package Text2;

import java.sql.ResultSet;

public class QuestionInfoDaoImpl extends BaseDao implements QuestionInfoDao {
	@Override
	public int operateDB(String sql, Object... obs) {
		// ������
		int result = 0;
		try {
			con = connectionDataBase();
			pst = con.prepareStatement(sql);
			if (obs != null) {
				for (int i = 0; i < obs.length; i++) {
					pst.setObject(i + 1, obs[i]);
				}
			}
			result = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ��д��ѯ����
	 */
	@Override
	public ResultSet queryDB(String sql, Object... obs) {
		try {
			con = connectionDataBase();
			pst = con.prepareStatement(sql);
			if (obs!=null) {
				for (int i = 0; i < obs.length; i++) {
					pst.setObject(i+1, obs[i]);
				}
			}
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
