import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class DerbyPlayer extends DerbyMode
{
    private boolean CanCrash;
    private String UpKey;
    private String DownKey;
    private String LeftKey;
    private String RightKey;
    private double CarHealth; // variável que guarda o valor da vida
    private double CarBoost; // variável que guarda o valor do 'boost'
    public DerbyPlayer(String UpKey, String DownKey, String LeftKey, String RightKey)
    {
        this.CanCrash = true;
        this.CarHealth = 100;
        this.CarBoost = 0;
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
    /**
     * movePlayer
     * 
     * Método para andar com o carro para a frente ou para trás
     */
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
    /**
     * rotatePlayer
     * 
     * Método para rodar para a esquerda ou para a direita
     */
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
        if(getOneIntersectingObject(DerbyPlayer.class) != null && CanCrash) // Se houver colisão entre dois carros e puder bater outra vez
        {
           CanCrash = false; // O carro já não pode bater outra vez
           GameModeMenu.getCrashSound().play(); // O som da colisão é ouvido               
           turn(180);
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
           newAngleAtTurn();
           move(10);
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
     * newAngleAtTurn
     * 
     * Método que deduz o novo ângulo do carro após a colisão com uma das paredes
     */
    private void newAngleAtTurn()
    {
       if(getRotation() <= 90 || (getRotation() >= 180 && getRotation() <= 270))
       {
           if(getX() == 0 || getX() == 599)
                turn(100);
           else
                turn(-100);
       }
       else
       {
           if(getRotation() > 270 || (getRotation() > 90 && getRotation() < 180))
           {
               if(getX() == 0 || getX() == 599)
                    turn(-100);
               else
                    turn(100);
           }
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
