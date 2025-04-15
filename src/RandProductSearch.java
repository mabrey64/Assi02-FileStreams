import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;


/** *
 * RandProductSearch is a GUI application that allows users to search for products
 * in a binary file. The application reads product data from a file named "products.dat"
 * and displays the search results in a text area.
 */

public class RandProductSearch extends JFrame {
    JPanel mainPanel;
    JTextField searchField;
    JTextArea resultArea;

    public RandProductSearch() {
        // Set the title, size, default close operation, and location of the frame
        setTitle("RandProductSearch");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        searchField = new JTextField(20);
        resultArea = new JTextArea(20, 50);
        resultArea.setEditable(false);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText();
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                searchProduct(searchTerm);
            } else {
                resultArea.setText("Please enter a product name to search for.\n");
            }
        });

        mainPanel.add(new JLabel("Enter Product Name to Search:"));
        mainPanel.add(searchField);
        mainPanel.add(searchButton);
        mainPanel.add(new JScrollPane(resultArea));

        add(mainPanel);

        setVisible(true);
    }

    /**
     * This method searches for a product by its name in the binary file "products.dat".
     * It reads the file and displays the matching products in the result area.
     *
     * @param productName The name of the product to search for.
     */
    public void searchProduct(String productName) {
        try (RandomAccessFile raf = new RandomAccessFile("products.dat", "r")) {
            resultArea.setText(""); // Clear previous results
            String searchTermLower = productName.toLowerCase();
            boolean foundMatch = false;

            // Read the file until EOF
            while (true) {
                try {
                    // Read the product data from the file
                    // Assuming the file structure is: ID (String), Name (String), Description (String), Cost (double)
                    String id = raf.readUTF();
                    String name = raf.readUTF();
                    String description = raf.readUTF();
                    double cost = raf.readDouble();

                    // Check if the product name contains the search term (case-insensitive)
                    if (name.toLowerCase().contains(searchTermLower)) {
                        String output = "ID: " + id + "\n" +
                                "Name: " + name + "\n" +
                                "Description: " + description + "\n" +
                                "Cost: " + String.format("%.2f", cost) + "\n\n"; // Format cost to 2 decimal places
                        resultArea.append(output);
                        foundMatch = true;
                    }
                } catch (java.io.EOFException e) {
                    break; // Exit the loop when the end of the file is reached
                }
            }

            // If no matches were found, display a message
            if (!foundMatch) {
                resultArea.append("No products found matching '" + productName + "'.\n");
            }

        } catch (java.io.IOException e) {
            resultArea.setText("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RandProductSearch());
    }
}