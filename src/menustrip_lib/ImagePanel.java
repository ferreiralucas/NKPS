/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menustrip_lib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author T
 */
 public class ImagePanel extends JPanel{
        
        private BufferedImage image;
        
        public ImagePanel(){
            this.image = null;
        }
        
        public ImagePanel(BufferedImage image){
            this.image = image;
        }
        
        public void setImage(BufferedImage image){
            this.image = image;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            if (this.image != null) {
                g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
    }
