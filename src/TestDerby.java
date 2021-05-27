import java.sql.Connection;
import java.sql.*;

public class TestDerby {

    private static  Connection conn = null;

    public static void main(String args[]) throws SQLException {
        try {
            //obtenemos el driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Creando la base de datos");
            //obtenemos la conexión
            conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Derby.DB;create=true");
            if (conn != null) {
                System.out.println("Base de datos creada correctamente");
                String creartabla = "create table CLIENTES(nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(120))";
                String cmd = "insert into CLIENTES values ('Jose Miguel','Gonzalez','Ayala','Camps Blancs 84')";
                try {
                    PreparedStatement stmt = conn.prepareStatement(creartabla);
                    stmt.execute();
                    stmt.close();

                    System.out.println("Tabla creada correctamente");

                    Statement stmt2 = conn.createStatement();
                    stmt2.executeUpdate(cmd);
                    stmt2.close();

                    System.out.println("registro insertado correctamente");

                    stmt2 = conn.createStatement();
                    String sql = "SELECT * FROM Clientes";
                    ResultSet rs = stmt2.executeQuery(sql);

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
                    stmt2.close();


                } catch (SQLException ex) {
                    System.out.println("Error creando tabla " + ex.toString());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " Error creando base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + " Error inesperado");
        }
        finally {
            conn.close();
            System.out.println("Conexión cerrada");
            }

    }
}
