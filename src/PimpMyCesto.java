import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class PimpMyCesto extends World
{
    // Array com as cores que vão ser usadas como 'tint' no carro
    private Color CarColors[] = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.CYAN,Color.BLUE,Color.MAGENTA,Color.GRAY};
    
    private static GreenfootImage SelectedCarP1;
    private static GreenfootImage SelectedCarP2;
    private int CurrentImage;
    private static String P1Name;
    private static String P2Name;
    private Car Car = new Car();
    private GreenfootImage Car_image;
    private ArrowL ArrowL = new ArrowL();
    private ArrowR ArrowR = new ArrowR();
    
    // booleanos usados para controlar a repetição do clique nas setas
    private boolean ArrowLClicked = false;
    private boolean ArrowRClicked = false; 
    
    //Array que guarda as cores em RGB dos pixeis da imagem original do carro
    private double[][] PixelsGray;
    
    //Array que guarda a transparência dos pixeis da imagem original do carro
    private double[][] PixelsAlpha;
    
    private final int FIRST_CAR = 1;
    private final int LAST_CAR = 8;
    public PimpMyCesto()
    {    
        super(600, 400, 1);
        SelectedCarP1 = null;
        SelectedCarP2 = null;
        prepare();
    }
    public void prepare()
    {
        setBackground("pimpmycesto.png");
        prepareCar();
        addObject(Car,300,250);
        spawnArrows();
        addObject(MainMenu.getMuteButton(),35,365);
        CurrentImage = 1;
    }
    /**
     * prepareCar()
     * 
     * Método que serve para definir a imagem inicial do carro (no menu de seleção)
     */
    private void prepareCar()
    {
        Car.setImage("images/carrocesto1_normal.png");
        Car.getImage().scale(150,112);
        Car_image = Car.getImage();
        PixelsGray = new double[Car_image.getWidth()][Car_image.getHeight()];
        PixelsAlpha = new double[Car_image.getWidth()][Car_image.getHeight()];
        getPixels();
        Car.setImage(tint(Car.getImage(),CarColors[0]));
    }
    /**
     * spawnArrows()
     * 
     * Define a imagem das setas (esquerda e direita) e adiciona-as ao mundo
     */
    private void spawnArrows()
    {
        ArrowL.setImage("arrow_left.png");
        ArrowR.setImage("arrow_right.png");
        ArrowL.getImage().setTransparency(0);        
        addObject(ArrowL,100,250);
        addObject(ArrowR,500,250);
    }
    public void act()
    {
        checkClickL();
        checkClickR();
        hideIfFirstCar();
        hideIfLastCar();
        pickCars();
    }
    /**
     * advanceToGame()
     * 
     * Método que serve para prosseguir para a seleção do jogo (só é invocado após ambos os carros são seleccionados)
     */
    private void advanceToGame()
    {
        Greenfoot.setWorld(new GameModeMenu());
    }
    /**
     * hideIfFirstCar()
     * 
     * Método que serve para 'esconder' a seta para a esquerda quando não há mais carros seleccionáveis para a esquerda
     */
    private void hideIfFirstCar()
    {
        if(CurrentImage == FIRST_CAR && ArrowL.getImage().getTransparency() == 255)
        // Se for o primeiro carro
            ArrowL.getImage().setTransparency(0);
        else
        // Caso contrário
            if(CurrentImage != FIRST_CAR && ArrowL.getImage().getTransparency() == 0)
                ArrowL.getImage().setTransparency(255);
    }
    /**
     * hideIfLastCar()
     * 
     * Método que serve para 'esconder' a seta para a esquerda quando não há mais carros seleccionáveis para a direita
     */
    private void hideIfLastCar()
    {
        if(CurrentImage == LAST_CAR && ArrowR.getImage().getTransparency() == 255)
        // Se for o último carro
            ArrowR.getImage().setTransparency(0);
        else
        // Caso contrário
            if(CurrentImage != LAST_CAR && ArrowR.getImage().getTransparency() == 0) 
                ArrowR.getImage().setTransparency(255);
    }
    /**
     * checkClickL()
     * 
     * Método que verifica se há um clique na seta para a esquerda ou um clique na tecla 'A'.
     * Caso tal aconteça, é mostrado o carro anterior
     */
    private void checkClickL()
    {        
        if((Greenfoot.mouseClicked(ArrowL) || Greenfoot.isKeyDown("a")) && !ArrowLClicked)
        // Caso o utilizador tenha clicado na seta para a esquerda (ou na tecla 'A')
        {
            ArrowLClicked = true; 
            if(CurrentImage > 1)
            {
                CurrentImage--;
                
                // É feito o 'tint' respetivo
                Car.setImage(tint(Car_image,CarColors[CurrentImage - 1]));
                
                MainMenu.getClickSound().play();
            }            
        }
        else
            if(!Greenfoot.mouseClicked(ArrowL) && !Greenfoot.isKeyDown("a") && ArrowLClicked)
                ArrowLClicked = false;
    }
    /**
     * checkClickR()
     * 
     * Método que verifica se há um clique na seta para a direita ou um clique na tecla 'D'.
     * Caso tal aconteça, é mostrado o carro seguinte
     */
    private void checkClickR()
    {
        if((Greenfoot.mouseClicked(ArrowR) || Greenfoot.isKeyDown("d")) && !ArrowRClicked)
        // Caso o utilizador tenha clicado na seta para a direita (ou na tecla 'D')
        {
            ArrowRClicked = true;
            if(CurrentImage < LAST_CAR)
            // Se não for o último carro
            {
                CurrentImage++;
                
                // É feito o 'tint' respetivo
                Car.setImage(tint(Car_image,CarColors[CurrentImage - 1]));
                
                MainMenu.getClickSound().play();            
            }            
        }
        else
            if(!Greenfoot.mouseClicked(ArrowR) && !Greenfoot.isKeyDown("d") && ArrowRClicked)
                ArrowRClicked = false;
    }
    /**
     * getPixels()
     * 
     * Método usado para obter os pixéis originais da imagem do carro, que são guardados num array
     * Serve para, depois, ser feito o 'tint' à imagem original quando é clicado para a esquerda ou para a direita
     */
    private void getPixels()
    {
        int width = Car_image.getWidth(), height = Car_image.getHeight();
        for (int y=0; y<height-1; y++)
            for (int x=0; x<width-1; x++) 
            {
                Color ic = Car_image.getColorAt(x, y);
                
                // É guardada a média dos valores RGB (gray) do pixel
                PixelsGray[x][y]=(ic.getRed() + ic.getGreen() + ic.getBlue())/3; 
                
                // É guardada a transparência do pixel
                PixelsAlpha[x][y]=ic.getAlpha(); 
            }
    }
    /**
     * tint
     * 
     * Método usado para mudar a tonalidade da imagem do carro, de forma a desenhar variações do carro (sendo uma delas seleccionada por cada jogador)
     */
    public GreenfootImage tint(GreenfootImage i, Color c) 
    {
        double r = c.getRed() / 255.0, g = c.getGreen() / 255.0, b = c.getBlue() / 255.0;

        int width = Car_image.getWidth(), height = Car_image.getHeight();
        GreenfootImage n = new GreenfootImage(width, height);
        
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                // é feito o 'tint' a cada pixel
                n.setColorAt(x, y, new Color((int)(r*PixelsGray[x][y]), (int)(g*PixelsGray[x][y]), (int)(b*PixelsGray[x][y]), (int)(PixelsAlpha[x][y])));

        return n; // é retornado o carro com o novo 'tint'
    }
    /**
     * pickCars()
     * 
     * Método que verifica se o carro é clicado ou é clicado na tecla 'ENTER' (para seleccionar o carro)
     * Caso tal aconteça, é pedido o nome do jogador e é avançado para a escolha do carro do jogador seguinte
     * Caso ambos os jogadores já tenham seleccionado o respetivo carro, é prosseguido para a seleção do jogo a ser jogado
     */
    private void pickCars()
    {
        if(SelectedCarP1 == null) // Caso o Jogador 1 ainda não tenha seleccionado o seu carro
        {
            if(Greenfoot.mousePressed(Car) || Greenfoot.isKeyDown("enter"))
            // Caso tenha seleccionado o carro (clicando no carro ou clicando no 'ENTER')
            {
                // É pedido o nome do Jogador 1
                P1Name = Greenfoot.ask("Please enter Player 1's name:");
                
                MainMenu.getClickSound().play();
                
                // É atribuida a imagem atual do carro ao Jogador 1
                SelectedCarP1 = new GreenfootImage(Car.getImage());
                SelectedCarP1.scale(75,56);
            }
        }
        else // Caso o Jogador 1 já tenha seleccionado o seu carro
        {
             if(SelectedCarP2 == null)
             // Caso o Jogador 2 ainda não tenha seleccionado o seu carro
             {
                    if(Greenfoot.mousePressed(Car) || Greenfoot.isKeyDown("enter"))
                    // Caso tenha seleccionado o carro (clicando no carro ou clicando no 'ENTER')
                    {
                        // É pedido o nome do Jogador 2
                        P2Name = Greenfoot.ask("Please enter Player 2's name:");
                        
                        MainMenu.getClickSound().play();
                        
                        // É atribuida a imagem atual do carro ao Jogador 1
                        SelectedCarP2 = new GreenfootImage(Car.getImage());
                        SelectedCarP2.scale(75,56);
                        
                        // É prosseguido para o menu da seleção do jogo
                        advanceToGame();
                    }
             }
        }
    }
    /**
     * getP1Car()
     * 
     * Getter que devolve a referência ao carro escolhido pelo Jogador 1
     */
    public static GreenfootImage getP1Car()
    {
        return SelectedCarP1;
    }
    /**
     * getP2Car()
     * 
     * Getter que devolve a referência ao carro escolhido pelo Jogador 2
     */
    public static GreenfootImage getP2Car()
    {
        return SelectedCarP2;
    }
    /**
     * getP1Name()
     * 
     * Getter que devolve a referência ao nome do Jogador 1
     */
    public static String getP1Name()
    {
        return P1Name;
    }
    /**
     * getP2Name()
     * 
     * Getter que devolve a referência ao nome do Jogador 2
     */
    public static String getP2Name()
    {
        return P2Name;
    }  
}