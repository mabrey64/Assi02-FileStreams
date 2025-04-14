/**

 */

public class Product
{
    private String ID;
    private String Name;
    private String Description;
    private double Cost;

    /**
     * The constructor for the Product class
     * @param ID The ID of the product
     * @param Name The name of the product
     * @param Description The description of the product
     * @param Cost The cost of the product
     */
    public Product(String ID, String Name, String Description, double Cost)
    {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Cost = Cost;
    }

    public String formatField(String ID, String Name, String Description) {
        return String.format("%-6s %-75s %-35s", ID, Name, Description);
    }

    /**
     * The toString method is used to return the value of the attributes as a string
     * @return The value of the attributes as a string
     */
//    @Override
    public String toString() {
        return ID + ", " + Name + ", " + Description + ", " + Cost;
    }

    /**
     * All the get methods are used to return the value of the attribute.
     * @return The value of the attribute.
     */
    public String getName() {
        return Name;
    }

    /**
     * The getID method is used to return the value of the ID attribute
     * @return The value of the ID attribute
     */
    public String getID() {
        return ID;
    }

    /**
     * The getDescription method is used to return the value of the Description attribute
     * @return The value of the Description attribute
     */
    public String getDescription() {
        return Description;
    }

    /**
     * The getCost method is used to return the value of the Cost attribute
     * @return The value of the Cost attribute
     */
    public double getCost() {
        return Cost;
    }


}