import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyPlayer extends Actor
{
    private boolean CanCrash;
    private String UpKey;
    private String DownKey;
    private String LeftKey;
    private String RightKey;
    private double CarHealth = 100; // variável que guarda o valor da vida
    private double CarBoost = 0; // variável que guarda o valor do 'boost'
    public DerbyPlayer(String UpKey, String DownKey, String LeftKey, String RightKey)
    {
        CanCrash = true;
        CarHealth = 100;
        CarBoost = 0;
        this.UpKey = UpKey;
        this.DownKey = DownKey;
        this.LeftKey = LeftKey;
        this.RightKey = RightKey;
    }
    public void act() 
    {
        movePlayer();
        rotatePlayer();
        turnAtEdge();
        checkCarCollision();
        regulateCarSpeed();
        pickBoost();
        touchBarrel();   
    }
    private void movePlayer()
    {
        if(Greenfoot.isKeyDown(UpKey))
        {
            if(CarBoost > 0)
                move(10);
            else
                move(5);
        }
        else
        {
            if(Greenfoot.isKeyDown(DownKey))
            {
                if(CarBoost > 0)
                    move(-10);
                else
                    move(-5);
            }
        }
    }
    private void rotatePlayer()
    {
        if(Greenfoot.isKeyDown(LeftKey))
            turn(-5);
        else
            if(Greenfoot.isKeyDown(RightKey))
                turn(5);
    }
    /**
     * touchBarrel
     * 
     * Método que deteta a colisão de um carro com um barril (caso colida, o carro perde vida)
     */
    private void touchBarrel()
    {
        Barrel barrel = (Barrel)getOneIntersectingObject(Barrel.class);
        if(barrel != null) // Se tiver batido num barril
        {
            GameModeMenu.getExplosionSound().play(); // O som da explosão é ouvido
            getWorld().removeObject(barrel); // O barril é removido do mundo
            CarHealth-=6;
        }
    }
    /**
     * checkCarCollision
     * 
     * Método que deteta a colisão entre carros
     */
    private void checkCarCollision()
    {
        DerbyPlayer carro = (DerbyPlayer) getOneIntersectingObject(DerbyPlayer.class);
        if(carro != null) // Se houver colisão entre dois carros
        {
           if(CanCrash) // Se o carro pode bater
           {
               CanCrash = false; // O carro já não pode bater outra vez
               GameModeMenu.getCrashSound().play(); // O som da colisão é ouvido               
               if(Greenfoot.getRandomNumber(2) == 1)
                    turn(Greenfoot.getRandomNumber(90)+90); // Vira [90,180]º
               else
                    turn(-Greenfoot.getRandomNumber(90)-90);// Vira [-180,-90]º               
               if(CarBoost > 0) // Se o carro tiver algum 'boost' 
               {
                   move(20); 
                   CarHealth-=1;
               }
               else // Caso contrário
               {
                   move(5);
                   CarHealth-=4;
               }
           }
        }
        else // Caso não há colisão
        {
            if(!CanCrash && !isAtEdge())
                CanCrash = true; // Já pode bater outra vez
        }
    }
    /**
     * turnAtEdge
     * 
     * Método que deteta se um carro toca nos limites do mapa
     */
    private void turnAtEdge()
    {
        if(isAtEdge() && CanCrash) // Se um carro estiver nos limites do mundo
        {          
           CanCrash = false; // já não pode bater outra vez
           GameModeMenu.getCrashSound().play(); // som da colisão é ouvido
           turn(180);
           if(CarBoost > 0) // Se tiver algum boost no momento
                CarHealth -= 4;
           else // Caso contrário
                CarHealth -= 1;
        }
        else // Se não estiver nos limites do mundo
        {
            if(!isAtEdge() && !CanCrash)
                CanCrash = true; // Já pode bater outra vez
        }
    }
    /**
     * regulateCarSpeed
     * 
     * Método que regula a velocidade do carro (diminuindo gradualmente o 'boost')
     */
    private void regulateCarSpeed()
    {
        if(CarBoost > 0)
            CarBoost-=0.2;
    }
    /**
     * getCarHealth()
     * 
     * Getter que retorna a referência à variável de instância que armazena a vida do Jogador 1
     */
    public double getCarHealth()
    {
        return CarHealth;
    }
    /**
     * pickBoost
     * 
     * Método que deteta se é apanhado algum 'boost'
     */
    private void pickBoost()
    {
        BoostPickup PickedBoost = (BoostPickup)getOneIntersectingObject(BoostPickup.class);
        if(PickedBoost != null && CarBoost <= 0) // Se for apanhado algum 'boost' e já não tiver de momento algum 'boost'
        {
            GameModeMenu.getBoostSound().play(); // som do 'boost' é ouvido
            getWorld().removeObject(PickedBoost); // é removido do mundo o 'boost'
            CarBoost = 100;
        }
    }
}
