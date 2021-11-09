package com.csf.base.loading;

import java.sql.SQLException;
import java.util.Map;

public class SampleLoadingModule {
	private static SampleLoadingModule _inst;

//	private static Logger log = null;

	private static Map<String, SampleLoadingModuleElements> kobetsuModuleCache = null;

	static {
		try {
//			log  = new Logger("KobetsuModule");
			_inst = new SampleLoadingModule();
		}
		catch (SQLException e) {
//			log.fatal(Message.getMessage("error.AE9079"), e);
			e.printStackTrace();
		}
	}

	private SampleLoadingModule() throws SQLException {
//		ResultSet rs = null;
//
//		try {
//			IExecuteRepository executeRepository = ExecuteRepositoryFactory.getInstance().getRepository();
//
//			kobetsuModuleCache = new HashMap<>();
//
//			String sql =
//				"SELECT WMPGID, WSEQNO, WCOMAD, WDSCIG, WSOKCD, WKITCD " +
//				"FROM RKBMJP1 " +
//				"ORDER BY WMPGID, WSEQNO";
//
//			Query query = executeRepository.createNativeQuery(sql);
//			rs = executeRepository.getResultList(query, sql);
//
//			while (rs.next()) {
//				KobetsuModuleElements obj = new KobetsuModuleElements();
//				obj.modulePgmid = rs.getString("WMPGID");
//				obj.seq = rs.getInt("WSEQNO");
//				obj.dataSchemaName = rs.getString("WDSCIG");
//				obj.command = rs.getString("WCOMAD");
//				obj.sokoCode = rs.getString("WSOKCD");
//				obj.kitakushaCode = rs.getString("WKITCD");
//				kobetsuModuleCache.put(
//						BeanUtil.nvl(rs.getString("WMPGID")) + "_" +
//						BeanUtil.nvl(rs.getString("WDSCIG")) + "_" +
//						BeanUtil.nvl(rs.getString("WSOKCD")) + "_" +
//						BeanUtil.nvl(rs.getString("WKITCD"))
//						, obj);
//			}
//		}
//		catch (SQLException e) {
//			log.fatal(Message.getMessage("error.AE8228"), e);
//			throw e;
//		}
//		finally {
//			if (rs != null) {
//				rs.close();
//			}
//		}
	}

	public static String getModuleProgramId(String modulePgmid, String databaseSchema, String sokoCode, String kitakushaCode) {
		String prmModulePgmid = BeanUtil.nvl(modulePgmid);
		String prmDatabaseSchema = BeanUtil.nvl(databaseSchema);
		String prmSokoCode = BeanUtil.nvl(sokoCode);
		String prmKitakushaCode = BeanUtil.nvl(kitakushaCode);

		SampleLoadingModuleElements elem = null;
		elem = (SampleLoadingModuleElements) kobetsuModuleCache.get(prmModulePgmid + "_" + prmDatabaseSchema + "_" + prmSokoCode + "_" + prmKitakushaCode);
		if (elem != null) {
			return elem.getCommand();
		}

		elem = (SampleLoadingModuleElements) kobetsuModuleCache.get(prmModulePgmid + "_" + prmDatabaseSchema + "_" + prmSokoCode + "_");
		if (elem != null) {
			return elem.getCommand();
		}

		elem = (SampleLoadingModuleElements) kobetsuModuleCache.get(prmModulePgmid + "_" + prmDatabaseSchema + "__" + prmKitakushaCode);
		if (elem != null) {
			return elem.getCommand();
		}

		elem = (SampleLoadingModuleElements) kobetsuModuleCache.get(prmModulePgmid + "_" + prmDatabaseSchema + "__");
		if (elem != null) {
			return elem.getCommand();
		}

		elem = (SampleLoadingModuleElements) kobetsuModuleCache.get(prmModulePgmid + "___");
		if (elem != null) {
			return elem.getCommand();
		}

		return null;
	}

	public static SampleLoadingModule getInstance() {
		return _inst;
	}

	public synchronized static void refresh() throws SQLException {
		_inst = new SampleLoadingModule();
	}
}
