package Text2;

import java.sql.ResultSet;

public interface QuestionInfoDao {
	public abstract int operateDB(String sql,Object...obs);
	//²éÑ¯Êý¾Ý¿â
	public abstract ResultSet queryDB(String sql,Object...obs);
}
