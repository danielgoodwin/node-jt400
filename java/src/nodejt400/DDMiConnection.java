
package nodejt400;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDriver;
import com.ibm.jtopenlite.ddm.DDMConnection;

import org.json.simple.JSONObject;

class DDMiConnection implements ConnectionProvider
{
	private final Connection connection;

	public DDMiConnection(JSONObject jsonConf)
	throws Exception
	{
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.putAll(jsonConf);

		connection = com.ibm.jtopenlite.ddm.DDMConnection
			.getConnection(jsonConf.get("host"), 
			jsonConf.get("user"),
			jsonConf.get("password"));
	}

	@Override
	public Connection getConnection() throws Exception
	{
		return connection;
	}

	@Override
	public void returnConnection(Connection c) throws Exception
	{
	}

	@Override
	public void close()
	{
		try
		{
			connection.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}