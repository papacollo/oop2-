import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class RadioButtonDemo extends JFrame implements ActionListener {
    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
    private ButtonGroup buttonGroup;
    private JLabel imageLabel;
    private JPanel leftPanel, rightPanel;
    private String imagesPath = "images";

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
        String[] possibleFormats = {"jpeg", "jpg", "png", "gif", "bmp"};
        String imagePath = "";
        String imageFileName = "";
        String baseName = "";
        
        switch (petType) {
            case "Bird":
                baseName = "bird";
                break;
            case "Cat":
                baseName = "cat";
                break;
            case "Dog":
                baseName = "dog";
                break;
            case "Rabbit":
                baseName = "rabbit";
                break;
            case "Pig":
                baseName = "pig";
                break;
        }
        
        // Try to find the image file in different formats
        File imageFile = null;
        for (String format : possibleFormats) {
            String testPath = imagesPath + File.separator + baseName + "." + format;
            File testFile = new File(testPath);
            if (testFile.exists()) {
                imageFile = testFile;
                imagePath = testPath;
                imageFileName = baseName + "." + format;
                break;
            }
        }
        
        // Load and display the image
        if (imageFile != null && imageFile.exists()) {
            try {
                // Try using ImageIO first for better format support
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                if (bufferedImage != null) {
                    Image scaledImage = bufferedImage.getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                    imageLabel.setText("");
                } else {
                    // If ImageIO fails, try ImageIcon as fallback
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    if (imageIcon.getImage() != null && imageIcon.getIconWidth() > 0) {
                        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(scaledImage));
                        imageLabel.setText("");
                    } else {
                        imageLabel.setText("Error: Could not load " + imageFileName);
                        imageLabel.setIcon(null);
                    }
                }
            } catch (Exception e) {
                imageLabel.setText("Error loading image: " + e.getMessage());
                imageLabel.setIcon(null);
                System.err.println("Error loading " + imagePath + ": " + e.getMessage());
            }
        } else {
            imageLabel.setText("No image found for " + petType);
            imageLabel.setIcon(null);
        }
        
        rightPanel.revalidate();
        rightPanel.repaint();

        // Display message box
        JOptionPane.showMessageDialog(this, "You selected: " + petType, "Pet Selection", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo frame = new RadioButtonDemo();
            frame.setVisible(true);
        });
    }
}
