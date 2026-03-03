import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonDemo extends JFrame implements ActionListener {
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
    private ButtonGroup buttonGroup;
    private JLabel imageLabel;
    private JPanel leftPanel, rightPanel;

    public RadioButtonDemo() {
        // Set up the frame
        setTitle("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with two sections (left for buttons, right for image)
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Left panel for radio buttons
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(120, 250));

        // Create radio buttons
        birdButton = new JRadioButton("Bird", false);
        catButton = new JRadioButton("Cat", false);
        dogButton = new JRadioButton("Dog", false);
        rabbitButton = new JRadioButton("Rabbit", false);
        pigButton = new JRadioButton("Pig", true);

        // Add action listeners
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // Create button group
        buttonGroup = new ButtonGroup();
        buttonGroup.add(birdButton);
        buttonGroup.add(catButton);
        buttonGroup.add(dogButton);
        buttonGroup.add(rabbitButton);
        buttonGroup.add(pigButton);

        // Add buttons to left panel
        leftPanel.add(birdButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(catButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(dogButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(rabbitButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(pigButton);
        leftPanel.add(Box.createVerticalGlue());

        // Right panel for image
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(350, 250));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Display initial pet
        displayPet("Pig");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (birdButton.isSelected()) {
            displayPet("Bird");
        } else if (catButton.isSelected()) {
            displayPet("Cat");
        } else if (dogButton.isSelected()) {
            displayPet("Dog");
        } else if (rabbitButton.isSelected()) {
            displayPet("Rabbit");
        } else if (pigButton.isSelected()) {
            displayPet("Pig");
        }
    }

    private void displayPet(String petType) {
        // Create a colored panel representing the pet
        JPanel petPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                switch (petType) {
                    case "Bird":
                        drawBird(g2d, width, height);
                        break;
                    case "Cat":
                        drawCat(g2d, width, height);
                        break;
                    case "Dog":
                        drawDog(g2d, width, height);
                        break;
                    case "Rabbit":
                        drawRabbit(g2d, width, height);
                        break;
                    case "Pig":
                        drawPig(g2d, width, height);
                        break;
                }
            }
        };

        petPanel.setBackground(Color.WHITE);
        rightPanel.removeAll();
        rightPanel.add(petPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();

        // Display message box
        JOptionPane.showMessageDialog(this, "You selected: " + petType, "Pet Selection", JOptionPane.INFORMATION_MESSAGE);
    }

    private void drawBird(Graphics2D g, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Body (yellow)
        g.setColor(new Color(255, 200, 0));
        g.fillOval(centerX - 30, centerY - 20, 60, 40);

        // Head (yellow)
        g.fillOval(centerX - 15, centerY - 35, 30, 30);

        // Eye (black)
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 5, centerY - 30, 8, 8);

        // Beak (orange)
        g.setColor(new Color(255, 140, 0));
        g.fillPolygon(new int[]{centerX + 8, centerX + 25, centerX + 8}, new int[]{centerY - 25, centerY - 20, centerY - 15}, 3);

        // Wings (darker yellow)
        g.setColor(new Color(200, 160, 0));
        g.fillOval(centerX - 35, centerY - 10, 20, 25);
        g.fillOval(centerX + 15, centerY - 10, 20, 25);

        // Feet (orange)
        g.setColor(new Color(255, 140, 0));
        g.setStroke(new BasicStroke(2));
        g.drawLine(centerX - 10, centerY + 20, centerX - 10, centerY + 35);
        g.drawLine(centerX + 10, centerY + 20, centerX + 10, centerY + 35);
    }

    private void drawCat(Graphics2D g, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Body (orange)
        g.setColor(new Color(255, 140, 0));
        g.fillOval(centerX - 35, centerY, 70, 60);

        // Head
        g.fillOval(centerX - 30, centerY - 40, 60, 50);

        // Ears (triangles)
        g.fillPolygon(new int[]{centerX - 25, centerX - 35, centerX - 15}, new int[]{centerY - 35, centerY - 60, centerY - 55}, 3);
        g.fillPolygon(new int[]{centerX + 25, centerX + 35, centerX + 15}, new int[]{centerY - 35, centerY - 60, centerY - 55}, 3);

        // Eyes (white)
        g.setColor(Color.WHITE);
        g.fillOval(centerX - 15, centerY - 25, 12, 12);
        g.fillOval(centerX + 3, centerY - 25, 12, 12);

        // Pupils (black)
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 12, centerY - 22, 6, 8);
        g.fillOval(centerX + 6, centerY - 22, 6, 8);

        // Nose (pink)
        g.setColor(new Color(255, 192, 203));
        g.fillOval(centerX - 3, centerY - 10, 6, 6);

        // Mouth
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawLine(centerX, centerY - 5, centerX - 10, centerY);
        g.drawLine(centerX, centerY - 5, centerX + 10, centerY);

        // Whiskers
        g.drawLine(centerX - 25, centerY - 8, centerX - 40, centerY - 5);
        g.drawLine(centerX - 25, centerY, centerX - 40, centerY + 2);
        g.drawLine(centerX + 25, centerY - 8, centerX + 40, centerY - 5);
        g.drawLine(centerX + 25, centerY, centerX + 40, centerY + 2);
    }

    private void drawDog(Graphics2D g, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Body (brown)
        g.setColor(new Color(139, 69, 19));
        g.fillOval(centerX - 40, centerY - 10, 80, 50);

        // Head
        g.fillOval(centerX - 25, centerY - 50, 50, 45);

        // Ears (floppy)
        g.fillOval(centerX - 40, centerY - 45, 20, 35);
        g.fillOval(centerX + 20, centerY - 45, 20, 35);

        // Snout (lighter brown)
        g.setColor(new Color(180, 100, 40));
        g.fillOval(centerX - 15, centerY - 30, 30, 25);

        // Eyes
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 12, centerY - 40, 8, 8);
        g.fillOval(centerX + 4, centerY - 40, 8, 8);

        // Nose
        g.fillOval(centerX - 4, centerY - 22, 8, 8);

        // Mouth
        g.setStroke(new BasicStroke(2));
        g.drawLine(centerX, centerY - 18, centerX - 5, centerY - 10);
        g.drawLine(centerX, centerY - 18, centerX + 5, centerY - 10);

        // Tail
        g.setColor(new Color(139, 69, 19));
        g.drawLine(centerX + 40, centerY + 10, centerX + 60, centerY - 10);
    }

    private void drawRabbit(Graphics2D g, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Body (white/light gray)
        g.setColor(new Color(240, 240, 240));
        g.fillOval(centerX - 30, centerY - 10, 60, 50);

        // Head
        g.fillOval(centerX - 22, centerY - 45, 44, 40);

        // Ears (long)
        g.fillOval(centerX - 12, centerY - 80, 10, 50);
        g.fillOval(centerX + 2, centerY - 75, 10, 50);

        // Inner ears (pink)
        g.setColor(new Color(255, 192, 203));
        g.fillOval(centerX - 10, centerY - 75, 6, 40);
        g.fillOval(centerX + 4, centerY - 70, 6, 40);

        // Eyes (black)
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 10, centerY - 30, 8, 8);
        g.fillOval(centerX + 2, centerY - 30, 8, 8);

        // Nose (pink)
        g.setColor(new Color(255, 192, 203));
        g.fillOval(centerX - 3, centerY - 18, 6, 6);

        // Mouth
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1));
        g.drawLine(centerX, centerY - 12, centerX - 3, centerY - 5);
        g.drawLine(centerX, centerY - 12, centerX + 3, centerY - 5);

        // Front paws
        g.setColor(new Color(240, 240, 240));
        g.fillOval(centerX - 18, centerY + 40, 10, 15);
        g.fillOval(centerX + 8, centerY + 40, 10, 15);

        // Tail (fluffy)
        g.fillOval(centerX + 25, centerY, 20, 20);
    }

    private void drawPig(Graphics2D g, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Body (pink)
        g.setColor(new Color(255, 150, 180));
        g.fillOval(centerX - 45, centerY - 15, 90, 60);

        // Head
        g.fillOval(centerX - 35, centerY - 50, 70, 50);

        // Snout
        g.fillOval(centerX - 18, centerY - 25, 36, 25);

        // Ears
        g.fillPolygon(new int[]{centerX - 30, centerX - 45, centerX - 20}, new int[]{centerY - 45, centerY - 60, centerY - 55}, 3);
        g.fillPolygon(new int[]{centerX + 30, centerX + 45, centerX + 20}, new int[]{centerY - 45, centerY - 60, centerY - 55}, 3);

        // Eyes (black)
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 15, centerY - 35, 8, 8);
        g.fillOval(centerX + 7, centerY - 35, 8, 8);

        // Nostrils
        g.fillOval(centerX - 8, centerY - 13, 4, 4);
        g.fillOval(centerX + 4, centerY - 13, 4, 4);

        // Mouth
        g.setStroke(new BasicStroke(2));
        g.drawLine(centerX, centerY - 5, centerX - 5, centerY + 5);
        g.drawLine(centerX, centerY - 5, centerX + 5, centerY + 5);

        // Legs
        g.setColor(new Color(255, 150, 180));
        g.fillRect(centerX - 30, centerY + 45, 12, 20);
        g.fillRect(centerX - 8, centerY + 45, 12, 20);
        g.fillRect(centerX + 16, centerY + 45, 12, 20);
        g.fillRect(centerX + 38, centerY + 45, 12, 20);

        // Tail (curled)
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawLine(centerX + 45, centerY + 15, centerX + 55, centerY);
        g.drawLine(centerX + 55, centerY, centerX + 60, centerY - 10);
        g.drawLine(centerX + 60, centerY - 10, centerX + 55, centerY - 15);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo frame = new RadioButtonDemo();
            frame.setVisible(true);
        });
    }
}
