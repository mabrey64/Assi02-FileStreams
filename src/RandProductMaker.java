import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RandProductMaker extends JFrame
{
    private JTextField idField, nameField, descriptionField, costField;
    private JPanel buttonPanel;
    private ArrayList<Product> productList;
    public RandProductMaker()
    {
        // Initialize the product list
        productList = new ArrayList<>();

        // Set the title, size, default close operation, and location of the frame
        setTitle("RandProductMaker");
        setSize(800, 600);
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputPanel();
        buttonPanel();

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void inputPanel()
    {
        // Create the input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5,5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the labels and text fields
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Cost:"));
        costField = new JTextField();
        inputPanel.add(costField);

        // Add the input panel to the frame
        add(inputPanel, BorderLayout.CENTER);
    }

    public void buttonPanel()
    {
        // Create the button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> addProduct());
        buttonPanel.add(addButton);

        JButton saveButton = new JButton("Save Products");
        saveButton.addActionListener(e -> saveProducts());
        buttonPanel.add(saveButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);
        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addProduct()
    {
        // Validate the input
        if (!validateInput()) {
            return; // Validation failed, do not proceed
        }
        else if (validateInput()) {
            JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        // Get the input values
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        String cost = costField.getText();

        // Grab character limit for each field
        int idLimit = 6;
        int nameLimit = 35;
        int descriptionLimit = 75;

        // Pad the fields with spaces to meet the length requirements
        String paddedID = String.format("%-" + idLimit + "s", id).substring(0, idLimit);
        String paddedName = String.format("%-" + nameLimit + "s", name).substring(0, nameLimit);
        String paddedDescription = String.format("%-" + descriptionLimit + "s", description).substring(0, descriptionLimit);
        double parsedCost = Double.parseDouble(cost);

        // Create a new Product object and add it to the list
        Product product = new Product(id, name, description, Double.parseDouble(cost));
        productList.add(product);

        // Clear the input fields
        clearFields();
    }

    public void saveProducts()
    {
        // Save the products to a file (implementation not shown)
        // You can use the ProductWriter class to write the product list to a file
        // For example:
        // ProductWriter writer = new ProductWriter();
        // writer.writeProducts(productList);
    }

    public void clearFields()
    {
        // Clear the input fields
        idField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        costField.setText("");
    }

    public boolean validateInput()
    {
        int idLimit = 6;
        int nameLimit = 35;
        int descriptionLimit = 75;

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String cost = costField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || cost.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (id.length() > idLimit) {
            JOptionPane.showMessageDialog(this, "ID must not exceed " + idLimit + " characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (name.length() > nameLimit) {
            JOptionPane.showMessageDialog(this, "Name must not exceed " + nameLimit + " characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (description.length() > descriptionLimit) {
            JOptionPane.showMessageDialog(this, "Description must not exceed " + descriptionLimit + " characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(cost);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new RandProductMaker());
    }

}
