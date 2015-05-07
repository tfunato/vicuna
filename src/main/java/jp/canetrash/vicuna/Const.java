package jp.canetrash.vicuna;

public class Const {

	private Const() {
	}

	public static final String INIT_TABLE = "create table DAMAGE_REPORT_MAIL ("
			+ "MESSAGE_ID TEXT," + "ATTACK_DATE TEXT,"
			+ "OPPOSITE_AGENT_NAME TEXT," + " PORTAL_NAME TEXT,"
			+ "PORTAL_INTEL_URL TEXT," + "LONGITUDE TEXT, " + "LATITUDE TEXT "
			+ ");";
}
