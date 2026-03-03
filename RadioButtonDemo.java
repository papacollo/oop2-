import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        String imagePath = "";
        String imageFileName = "";
        
        switch (petType) {
            case "Bird":
                imageFileName = "bird.jpg";
                break;
            case "Cat":
                imageFileName = "cat.jpg";
                break;
            case "Dog":
                imageFileName = "dog.jpg";
                break;
            case "Rabbit":
                imageFileName = "rabbit.avif";
                break;
            case "Pig":
                imageFileName = "pig.jpg";
                break;
        }
        
        imagePath = imagesPath + File.separator + imageFileName;
        
        // Load and display the image
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            
            // Scale image to fit the panel
            Image scaledImage = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText("");
        } else {
            imageLabel.setText("Image not found: " + imagePath);
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
