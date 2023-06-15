package entity;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_Dryad extends Entity {

    public final int screenX;
    public final int screenY;

    public NPC_Dryad(GamePanel gp){
        super(gp);
        solidArea = new Rectangle(0,0,40,54);
        direction = "down";
        speed = 0;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        getDryadImage();
    }

    public void getDryadImage() {

        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_ari1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_ari2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
        }
        //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
