import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MuteButton extends MainMenuItems
{
    public void act()
    {
        checkMuteButton();
        playOrMuteBGM();
    }
    private void playOrMuteBGM()
    {
        if(!MainMenu.bailinho.isPlaying() && !MainMenu.bailinhoMuted)
            MainMenu.bailinho.playLoop();
        else
            if(MainMenu.bailinho.isPlaying() && MainMenu.bailinhoMuted)
                MainMenu.bailinho.stop();
    }
    private void checkMuteButton()
    {
        if(Greenfoot.mouseClicked(this))
        {
            MainMenu.click.play();
            MainMenu.bailinhoMuted = !MainMenu.bailinhoMuted;
        }
    }
}
