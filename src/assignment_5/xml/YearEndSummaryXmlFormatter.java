package assignment_5.xml;

import assignment_5.datamodel.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author Jan
 */
public class YearEndSummaryXmlFormatter extends XmlFormatter {

    // constructor for creating dom in parent class
    public YearEndSummaryXmlFormatter() throws ParserConfigurationException {
        // create instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // use factory to get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        // create instance of DOM
        super.dom = db.newDocument();
    }

    @Override
    public Document formatXml(Summary summary) {
        // create the root element
        createTag(XmlFormatter.Level.ROOT, "year_end_summary");

        // create data elements for year
        setYear(summary.getStartDate().toString(), summary.getEndDate().toString());

        // create data elements for customer_list   
        Set<Customer> customers = ((YearEndSummary) summary).getCustomers();
        setCustomerList(customers);

        // create data elements for product_list
        Map<String, Set<Product>> products = ((YearEndSummary) summary).getProducts();
        setProductList(products);

        // create data elements for staff_list
        Set<Employee> employees = ((YearEndSummary) summary).getEmployees();
        setStaffList(employees);

        return dom;
    }

    // set period of time
    private void setYear(String startDate, String endDate) {
        createTag(XmlFormatter.Level.LEVEL_1, "year");
        createTag(XmlFormatter.Level.LEVEL_2, "start_date", startDate);
        createTag(XmlFormatter.Level.LEVEL_2, "end_date", endDate);
    }

    // set customer list from data that extract from the database
    private void setCustomerList(Set<Customer> customers) {
        createTag(XmlFormatter.Level.LEVEL_1, "customer_list");
        // iterate through the customers
        Iterator iterator = customers.iterator();
        while (iterator.hasNext()) {
            createTag(XmlFormatter.Level.LEVEL_2, "customer");

            // get data from each row in customers
            Customer customer = (Customer) iterator.next();
            createTag(XmlFormatter.Level.LEVEL_3, "customer_name", customer.getCustomerName());

            createTag(XmlFormatter.Level.LEVEL_3, "address");
            createTag(XmlFormatter.Level.LEVEL_4, "street_address", customer.getAddress().getStreetAddress());
            createTag(XmlFormatter.Level.LEVEL_4, "city", customer.getAddress().getCity());
            createTag(XmlFormatter.Level.LEVEL_4, "postal_code", customer.getAddress().getPostalCode());
            createTag(XmlFormatter.Level.LEVEL_4, "country", customer.getAddress().getCountry());

            createTag(XmlFormatter.Level.LEVEL_3, "num_orders", String.valueOf(customer.getNumOrders()));
            createTag(XmlFormatter.Level.LEVEL_3, "order_value", String.valueOf(customer.getOrderValue()));
        }
    }

    // set product list from data that extract from the database
    private void setProductList(Map<String, Set<Product>> products) {
        createTag(XmlFormatter.Level.LEVEL_1, "product_list");
        // iterate through the products
        Iterator<Map.Entry<String, Set<Product>>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()) {
            createTag(XmlFormatter.Level.LEVEL_2, "product_set");

            Map.Entry<String, Set<Product>> productLine = iterator.next();
            createTag(XmlFormatter.Level.LEVEL_3, "product_line_name", productLine.getKey());

            Set<Product> productsSet = productLine.getValue();
            Iterator iteratorProduct = productsSet.iterator();
            while (iteratorProduct.hasNext()) {
                // get data from each row in products
                Product product = (Product) iteratorProduct.next();
                createTag(XmlFormatter.Level.LEVEL_3, "product");
                createTag(XmlFormatter.Level.LEVEL_4, "product_name", product.getProductName());
                createTag(XmlFormatter.Level.LEVEL_4, "product_vendor", product.getProductVendor());
                createTag(XmlFormatter.Level.LEVEL_4, "units_sold", String.valueOf(product.getUnitsSold()));
                createTag(XmlFormatter.Level.LEVEL_4, "total_sales", String.valueOf(product.getTotalSales()));
            }
        }
    }

    // set staff list from data that extract from the database
    private void setStaffList(Set<Employee> employees) {
        createTag(XmlFormatter.Level.LEVEL_1, "staff_list");
        // iterate through the employees
        Iterator iterator = employees.iterator();
        while (iterator.hasNext()) {
            createTag(XmlFormatter.Level.LEVEL_2, "employee");
            // get data from each row in employees
            Employee employee = (Employee) iterator.next();
            createTag(XmlFormatter.Level.LEVEL_3, "first_name", employee.getFirstName());
            createTag(XmlFormatter.Level.LEVEL_3, "last_name", employee.getLastName());
            createTag(XmlFormatter.Level.LEVEL_3, "office_city", employee.getOfficeCity());
            createTag(XmlFormatter.Level.LEVEL_3, "active_customers", String.valueOf(employee.getActiveCustomers()));
            createTag(XmlFormatter.Level.LEVEL_3, "total_sales", String.valueOf(employee.getTotalSales()));
        }
    }
}