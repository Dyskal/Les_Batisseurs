package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class found on StackOverflow, from Ladislav Gallay.
 * https://stackoverflow.com/a/23934901
 */
public class JColoredButton extends JButton implements MouseListener {
    private boolean hovered = false;
    private boolean clicked = false;

    private final Color normalColor;
    private final Color lightColor;
    private final Color darkColor;

    public JColoredButton(String text, Color backgroundColor, Color foregroundColor) {
        setForeground(foregroundColor);
        setText(text);

        this.normalColor = backgroundColor;
        this.lightColor = backgroundColor.brighter();
        this.darkColor = backgroundColor.darker();

        addMouseListener(this);
        setContentAreaFilled(false);
    }

    @Override
    public void setSize(Dimension size) {
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /**
     * Overpainting component, so it can have different colors
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gp;

        if (clicked)
            gp = new GradientPaint(0, 0, darkColor, 0, getHeight(), darkColor.darker());
        else if (hovered)
            gp = new GradientPaint(0, 0, lightColor, 0, getHeight(), lightColor.darker());
        else
            gp = new GradientPaint(0, 0, normalColor, 0, getHeight(), normalColor.darker());

        g2d.setPaint(gp);

        // Draws the rounded opaque panel with borders
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // For High quality
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 7, 7);

        g2d.setColor(darkColor.darker().darker());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 7, 7);

        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {
        hovered = true;
        clicked = false;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        hovered = false;
        clicked = false;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        hovered = true;
        clicked = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        hovered = true;
        clicked = false;
        repaint();
    }
}