package com.jet.variableView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCopyTest {
    public static void main(String[] args) {
        final BufferedImage testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = testImage.createGraphics();
        graphics.setColor(Color.RED);
        graphics.fill(new Rectangle(0, 0, 100, 100));
        graphics.dispose();

        System.out.print("Welcome to image copy!");
    }


}

