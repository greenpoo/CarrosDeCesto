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
        if(!MainMenu.getBailinho().isPlaying() && !MainMenu.isBailinhoMuted())
            MainMenu.getBailinho().playLoop();
        else
            if(MainMenu.getBailinho().isPlaying() && MainMenu.isBailinhoMuted())
                MainMenu.getBailinho().stop();
    }
    private void checkMuteButton()
    {
        if(Greenfoot.mouseClicked(this))
        {
            MainMenu.getClickSound().play();
            MainMenu.toggleBailinhoMuted();
        }
    }
}
