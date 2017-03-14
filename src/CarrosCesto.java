import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.time.Instant;
import java.time.Duration;
public class CarrosCesto extends Actor
{
    private static double CarHealthP1; // variável que guarda o valor da vida do Jogador 1
    private static double CarHealthP2; // variável que guarda o valor da vida do Jogador 2
    private static double CarBoostP1; // variável que guarda o valor do 'boost' do Jogador 1
    private static double CarBoostP2; // variável que guarda o valor do 'boost' do Jogador 2
    private Instant past = Instant.now();
    private float x = 0;
    private float y = 0;
    public void physicsAct(long delta) 
    {        
    }
    public final void act() {
        Instant now = Instant.now();
        physicsAct(Duration.between(past, now).toMillis());
        past = now;
    }
    /**
     * getP1Health()
     * 
     * Getter que retorna a referência à variável de instância que armazena a vida do Jogador 1
     */
    public static double getP1Health()
    {
        return CarHealthP1;
    }
    /**
     * getP2Health()
     * 
     * Getter que retorna a referência à variável de instância que armazena a vida do Jogador 2
     */
    public static double getP2Health()
    {
        return CarHealthP2;
    }
    /**
     * setP1Health()
     * 
     * Setter que atribui um novo valor à vida do Jogador 1
     */
    public static void setP1Health(double newHealth)
    {
        CarHealthP1 = newHealth;
    }
    /**
     * setP2Health()
     * 
     * Setter que atribui um novo valor à vida do Jogador 2
     */
    public static void setP2Health(double newHealth)
    {
        CarHealthP2 = newHealth;
    }
    /**
     * getP1Boost()
     * 
     * Getter que retorna a referência à variável de instância que armazena o 'boost' do Jogador 1
     */
    public static double getP1Boost()
    {
        return CarBoostP1;
    }
    /**
     * getP2Boost()
     * 
     * Getter que retorna a referência à variável de instância que armazena o 'boost' do Jogador 2
     */
    public static double getP2Boost()
    {
        return CarBoostP2;
    }
    /**
     * setP1Boost
     * 
     * Setter que atribui um novo valor ao 'boost' do Jogador 1
     */
    public static void setP1Boost(double newBoost)
    {
        CarBoostP1 = newBoost;
    }
    /**
     * setP2Boost
     * 
     * Setter que atribui um novo valor ao 'boost' do Jogador 2
     */
    public static void setP2Boost(double newBoost)
    {
        CarBoostP2 = newBoost;
    }
    /**
     * pickBoost
     * 
     * Método que deteta se é apanhado algum 'boost'
     */
    public double pickBoost(double CarBoost)
    {
        BoostPickup PickedBoost = (BoostPickup)getOneIntersectingObject(BoostPickup.class);
        if(PickedBoost != null && CarBoost <= 0) // Se for apanhado algum 'boost' e já não tiver de momento algum 'boost'
        {
            GameModeMenu.getBoostSound().play(); // som do 'boost' é ouvido
            getWorld().removeObject(PickedBoost); // é removido do mundo o 'boost'
            return 100;
        }
        else // Caso contrário
            return CarBoost;
    }
    /**
     * turnAtEdge
     * 
     * Método que deteta se um carro toca nos limites do mapa
     */
    public double turnAtEdge(double CarHealth,double CarBoost,boolean isP1)
    {
        if(isAtEdge()) // Se um carro estiver nos limites do mundo
        {
           if((isP1 && Player1.getCanCrash()) || (!isP1 && Player2.getCanCrash())) // Se o carro em questão pode bater
           {
               if(isP1) // Se foi o Jogador 1
                    Player1.toggleCanCrash(); // já não pode bater outra vez
               if(!isP1) // Se for o Jogador 2
                    Player2.toggleCanCrash(); // já não pode bater outra vez
               GameModeMenu.getCrashSound().play(); // som da colisão é ouvido
               turn(180);
               if(CarBoost > 0) // Se tiver algum boost no momento
                    return CarHealth - 4;
               else // Caso contrário
                    return CarHealth - 1;
           }
        }
        else // Se não estiver nos limites do mundo
        {
            if(isP1 && !Player1.getCanCrash())
                Player1.toggleCanCrash(); // Já pode bater outra vez
            if(!isP1 && !Player2.getCanCrash())
                Player2.toggleCanCrash(); // Já pode bater outra vez
        }
        return CarHealth;
    }
    /**
     * regulateCarSpeed
     * 
     * Método que regula a velocidade do carro (diminuindo gradualmente o 'boost')
     */
    public double regulateCarSpeed(double CarBoost)
    {
        if(CarBoost > 0)
            return CarBoost - 0.2;
        else
            return CarBoost;
    }    
    /**
     * touchBarrel
     * 
     * Método que deteta a colisão de um carro com um barril (caso colida, o carro perde vida)
     */
    public double touchBarrel(double CarHealth)
    {
        Barrel barrel = (Barrel)getOneIntersectingObject(Barrel.class);
        if(barrel != null) // Se tiver batido num barril
        {
            GameModeMenu.getExplosionSound().play(); // O som da explosão é ouvido
            getWorld().removeObject(barrel); // O barril é removido do mundo
            return CarHealth - 6;
        }
        else
            return CarHealth;
    }
    /**
     * checkCarCollision
     * 
     * Método que deteta a colisão entre carros
     */
    public double checkCarCollision(double CarHealth,double CarBoost,boolean isP1)
    {
        CarrosCesto carro = (CarrosCesto) getOneIntersectingObject(CarrosCesto.class);
        if(carro != null) // Se houver colisão entre dois carros
        {
           if((isP1 && Player1.getCanCrash()) || (!isP1 && Player2.getCanCrash())) // Se o carro pode bater
           {
               if(isP1)
                    Player1.toggleCanCrash(); // O carro já não pode bater outra vez
               if(!isP1)
                    Player2.toggleCanCrash(); // O carro já não pode bater outra vez
               GameModeMenu.getCrashSound().play(); // O som da colisão é ouvido               
               if(Greenfoot.getRandomNumber(2) == 1)
                    turn(Greenfoot.getRandomNumber(90)+90); // Vira [90,180]º
               else
                    turn(-Greenfoot.getRandomNumber(90)-90);// Vira [-180,-90]º               
               if(CarBoost > 0) // Se o carro tiver algum 'boost' 
               {
                   move(20); 
                   return CarHealth - 1;
               }
               else // Caso contrário
               {
                   move(5);
                   return CarHealth - 4;
               }
           }
        }
        else // Caso não há colisão
        {
            if(isP1 && !Player1.getCanCrash() && !isAtEdge())
                Player1.toggleCanCrash(); // Já pode bater outra vez
            if(!isP1 && Player2.getCanCrash() && !isAtEdge())
                Player2.toggleCanCrash(); // Já pode bater outra vez
        }
        return CarHealth;
    }   
}
