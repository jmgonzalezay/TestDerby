import java.sql.*;

public class TestH2 {

    private static Connection conn = null;

    public static void main(String args[]) throws SQLException {

        Connection connection= DriverManager.getConnection("jdbc:h2:mem:test");
        Statement s=connection.createStatement();

        try {
            s.execute("DROP TABLE CLIENTES");
        } catch(SQLException sqle) {
            System.out.println("Table not found, not dropping");
        }
        s.execute("create table CLIENTES(nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(120))");
        s.addBatch("insert into CLIENTES values ('Jose Miguel','Gonzalez','Ayala','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Jannette Darlene','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Darlene Arely','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Maria','Gonzalez','Velasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Jose Miguel','Gonzalez','Ayala','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Jannette Darlene','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Darlene Arely','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Maria','Gonzalez','Velasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Jose Miguel','Gonzalez','Ayala','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Jannette Darlene','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Darlene Arely','Velasquez','Vasquez','Camps Blancs 84')");
        s.addBatch("insert into CLIENTES values ('Maria','Gonzalez','Velasquez','Camps Blancs 84')");
        int[] countWithoutException = s.executeBatch();
        System.out.println("Inserted = " + countWithoutException.length);
        PreparedStatement ps=connection.prepareStatement("select * from CLIENTES order by 3 desc");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            //Retrieve by column name
            String nombre = rs.getString("nombre");
            String ape1 = rs.getString("ape1");
            String ape2 = rs.getString("ape2");
            String direccion = rs.getString("direccion");

            //Display values
            System.out.print("Nombre: " + nombre);
            System.out.print(", ape1: " + ape1);
            System.out.print(", ape2: " + ape2);
            System.out.println(", direccion: " + direccion);
        }
        rs.close();
        ps.close();
        s.close();
        connection.close();
    }
}
