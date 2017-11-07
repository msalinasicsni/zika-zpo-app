package ni.org.ics.zpo.appmovil.database;

/**
 * Adaptador de la base de datos Cohorte
 * 
 * @author William Aviles
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteQueryBuilder;
import ni.org.ics.zpo.appmovil.domain.users.Authority;
import ni.org.ics.zpo.appmovil.domain.users.UserSistema;
import ni.org.ics.zpo.appmovil.helpers.*;
import ni.org.ics.zpo.appmovil.utils.*;
import ni.org.ics.zpo.domain.*;

public class ZpoAdapter {

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	private final Context mContext;
	private final String mPassword;
	private final boolean mFromServer;
	private final boolean mCleanDb;
	

	public ZpoAdapter(Context context, String password, boolean fromServer, boolean cleanDb) {
		mContext = context;
		mPassword = password;
		mFromServer = fromServer;
		mCleanDb = cleanDb;
	}
	
	private static class DatabaseHelper extends ZpoSQLiteOpenHelper {

		DatabaseHelper(Context context, String password, boolean fromServer, boolean cleanDb) {
			super(FileUtils.DATABASE_PATH, MainDBConstants.DATABASE_NAME, MainDBConstants.DATABASE_VERSION, context,
					password, fromServer, cleanDb);
			createStorage();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(MainDBConstants.CREATE_USER_TABLE);
			db.execSQL(MainDBConstants.CREATE_ROLE_TABLE);
            db.execSQL(MainDBConstants.CREATE_STATUS_MOTHER_TABLE);
            db.execSQL(MainDBConstants.CREATE_SCREENING_TABLE);
            db.execSQL(Zpo01DBConstants.CREATE_STUDYENTRY_AB_TABLE);
            db.execSQL(Zpo01DBConstants.CREATE_STUDYENTRY_C_TABLE);
            db.execSQL(Zpo01DBConstants.CREATE_STUDYENTRY_DF_TABLE);
            db.execSQL(Zpo02DBConstants.CREATE_BIOCOLLECTION_TABLE);
            db.execSQL(Zpo04DBConstants.CREATE_EXTENDED_AD_TABLE);
            db.execSQL(Zpo04DBConstants.CREATE_EXTENDED_E_TABLE);
            db.execSQL(Zpo04DBConstants.CREATE_EXTENDED_F_TABLE);
            db.execSQL(Zpo05DBConstants.CREATE_DELIVERY_TABLE);
            db.execSQL(Zpo08DBConstants.CREATE_STUDYEXIT_TABLE);
            db.execSQL(MainDBConstants.CREATE_INFANTDATA_TABLE);
            db.execSQL(MainDBConstants.CREATE_INFANTSTATUS_TABLE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onCreate(db);
		}	
	}

	public ZpoAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext,mPassword,mFromServer,mCleanDb);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	/**
	 * Crea un cursor desde la base de datos
	 * 
	 * @return cursor
	 */
	public Cursor crearCursor(String tabla, String whereString, String projection[], String ordenString) throws SQLException {
		Cursor c = null;
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(tabla);
		c = qb.query(mDb,projection,whereString,null,null,null,ordenString);
		return c;
	}

	public static boolean createStorage() {
		return FileUtils.createFolder(FileUtils.DATABASE_PATH);
	}
	
	/**
	 * Metodos para usuarios en la base de datos
	 * 
	 * @param user
	 *            Objeto Usuario que contiene la informacion
	 *
	 */
	//Crear nuevo usuario en la base de datos
	public void crearUsuario(UserSistema user) {
		ContentValues cv = UserSistemaHelper.crearUserSistemaContentValues(user);
		mDb.insert(MainDBConstants.USER_TABLE, null, cv);
	}
	//Editar usuario existente en la base de datos
	public boolean editarUsuario(UserSistema user) {
		ContentValues cv = UserSistemaHelper.crearUserSistemaContentValues(user);
		return mDb.update(MainDBConstants.USER_TABLE, cv, MainDBConstants.username + "='" 
				+ user.getUsername()+"'", null) > 0;
	}
	//Limpiar la tabla de usuarios de la base de datos
	public boolean borrarUsuarios() {
		return mDb.delete(MainDBConstants.USER_TABLE, null, null) > 0;
	}
	//Obtener un usuario de la base de datos
	public UserSistema getUsuario(String filtro, String orden) throws SQLException {
		UserSistema mUser = null;
		Cursor cursorUser = crearCursor(MainDBConstants.USER_TABLE, filtro, null, orden);
		if (cursorUser != null && cursorUser.getCount() > 0) {
			cursorUser.moveToFirst();
			mUser=UserSistemaHelper.crearUserSistema(cursorUser);
		}
		if (!cursorUser.isClosed()) cursorUser.close();
		return mUser;
	}
	//Obtener una lista de usuarios de la base de datos
	public List<UserSistema> getUsuarios(String filtro, String orden) throws SQLException {
		List<UserSistema> mUsuarios = new ArrayList<UserSistema>();
		Cursor cursorUsuarios = crearCursor(MainDBConstants.USER_TABLE, filtro, null, orden);
		if (cursorUsuarios != null && cursorUsuarios.getCount() > 0) {
			cursorUsuarios.moveToFirst();
			mUsuarios.clear();
			do{
				UserSistema mUser = null;
				mUser = UserSistemaHelper.crearUserSistema(cursorUsuarios);
				mUsuarios.add(mUser);
			} while (cursorUsuarios.moveToNext());
		}
		if (!cursorUsuarios.isClosed()) cursorUsuarios.close();
		return mUsuarios;
	}
	
	/**
	 * Metodos para roles en la base de datos
	 * 
	 * @param rol
	 *            Objeto Authority que contiene la informacion
	 *
	 */
	//Crear nuevo rol en la base de datos
	public void crearRol(Authority rol) {
		ContentValues cv = UserSistemaHelper.crearRolValues(rol);
		mDb.insert(MainDBConstants.ROLE_TABLE, null, cv);
	}
	//Limpiar la tabla de roles de la base de datos
	public boolean borrarRoles() {
		return mDb.delete(MainDBConstants.ROLE_TABLE, null, null) > 0;
	}
	//Verificar un rol de usuario
	public Boolean buscarRol(String username, String Rol) throws SQLException {
		Cursor c = mDb.query(true, MainDBConstants.ROLE_TABLE, null,
				MainDBConstants.username + "='" + username + "' and " + MainDBConstants.role + "='" + Rol + "'" , null, null, null, null, null);
		boolean result = c != null && c.getCount()>0; 
		c.close();
		return result;
	}
	
	public Boolean verificarData() throws SQLException{
		return true;
		
	}

    /**
     * Metodos para Zpo00Screening en la base de datos
     *
     * @param screening
     *            Objeto Zpo00Screening que contiene la informacion
     *
     */
    //Crear nuevo Zpo00Screening en la base de datos
    public void crearZpo00Screening(Zpo00Screening screening) {
        ContentValues cv = Zpo00ScreeningHelper.crearZpo00ScreeningValues(screening);
        mDb.insert(MainDBConstants.SCREENING_TABLE, null, cv);
    }
    //Editar Zpo00Screening existente en la base de datos
    public boolean editarZpo00Screening(Zpo00Screening screening) {
        ContentValues cv = Zpo00ScreeningHelper.crearZpo00ScreeningValues(screening);
        return mDb.update(MainDBConstants.SCREENING_TABLE, cv, MainDBConstants.recordId + "='"
                + screening.getRecordId()+"'", null) > 0;
    }
    //Limpiar la tabla de Zpo00Screening de la base de datos
    public boolean borrarZpo00Screening() {
        return mDb.delete(MainDBConstants.SCREENING_TABLE, null, null) > 0;
    }
    //Actualizar el estado la tabla de Zpo00Screening de la base de datos
    public int actualizarEstadoZpo00Screening(String filtro, String estado) {
        ContentValues cv = new ContentValues();
        cv.put(MainDBConstants.STATUS, estado);
        int numRegistros = mDb.update(MainDBConstants.SCREENING_TABLE, cv,filtro, null);
        return numRegistros;
    }
    //Obtener un Zpo00Screening de la base de datos
    public Zpo00Screening getZpo00Screening(String filtro, String orden) throws SQLException {
        Zpo00Screening mScreening = null;
        Cursor cursorScreening = crearCursor(MainDBConstants.SCREENING_TABLE, filtro, null, orden);
        if (cursorScreening != null && cursorScreening.getCount() > 0) {
            cursorScreening.moveToFirst();
            mScreening=Zpo00ScreeningHelper.crearZpo00Screening(cursorScreening);
        }
        if (!cursorScreening.isClosed()) cursorScreening.close();
        return mScreening;
    }
    //Obtener una lista de Zpo00Screening de la base de datos
    public List<Zpo00Screening> getZpo00Screenings(String filtro, String orden) throws SQLException {
        List<Zpo00Screening> mScreenings = new ArrayList<Zpo00Screening>();
        Cursor cursorScreenings = crearCursor(MainDBConstants.SCREENING_TABLE, filtro, null, orden);
        if (cursorScreenings != null && cursorScreenings.getCount() > 0) {
            cursorScreenings.moveToFirst();
            mScreenings.clear();
            do{
                Zpo00Screening mScreening = null;
                mScreening = Zpo00ScreeningHelper.crearZpo00Screening(cursorScreenings);
                mScreenings.add(mScreening);
            } while (cursorScreenings.moveToNext());
        }
        if (!cursorScreenings.isClosed()) cursorScreenings.close();
        return mScreenings;
    }

    /**
     * Metodos para Zpo01StudyEntrySectionAtoB en la base de datos
     *
     */
    //Crear nuevo Zpo01StudyEntrySectionAtoB en la base de datos
    public void crearZpo01StudyEntrySectionAtoB(Zpo01StudyEntrySectionAtoB studyEntrySectionAtoB) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionAtoBValues(studyEntrySectionAtoB);
        mDb.insert(Zpo01DBConstants.STUDYENTRY_AB_TABLE, null, cv);
    }
    //Editar Zpo01StudyEntrySectionAtoB existente en la base de datos
    public boolean editarZpo01StudyEntrySectionAtoB(Zpo01StudyEntrySectionAtoB studyEntrySectionAtoB) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionAtoBValues(studyEntrySectionAtoB);
        return mDb.update(Zpo01DBConstants.STUDYENTRY_AB_TABLE, cv, Zpo01DBConstants.recordId + "='"
                + studyEntrySectionAtoB.getRecordId()+ "' and " + Zpo01DBConstants.eventName + "='" + studyEntrySectionAtoB.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo01StudyEntrySectionAtoB de la base de datos
    public boolean borrarZpo01StudyEntrySectionAtoB() {
        return mDb.delete(Zpo01DBConstants.STUDYENTRY_AB_TABLE, null, null) > 0;
    }
    //Obtener un Zpo01StudyEntrySectionAtoB de la base de datos
    public Zpo01StudyEntrySectionAtoB getZpo01StudyEntrySectionAtoB(String filtro, String orden) throws SQLException {
        Zpo01StudyEntrySectionAtoB studyEntrySectionAtoB = null;
        Cursor cursorAtoD = crearCursor(Zpo01DBConstants.STUDYENTRY_AB_TABLE, filtro, null, orden);
        if (cursorAtoD != null && cursorAtoD.getCount() > 0) {
            cursorAtoD.moveToFirst();
            studyEntrySectionAtoB=Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionAtoB(cursorAtoD);
        }
        if (!cursorAtoD.isClosed()) cursorAtoD.close();
        return studyEntrySectionAtoB;
    }
    //Obtener una lista de Zpo01StudyEntrySectionAtoB de la base de datos
    public List<Zpo01StudyEntrySectionAtoB> getZpo01StudyEntrySectionAtoBs(String filtro, String orden) throws SQLException {
        List<Zpo01StudyEntrySectionAtoB> studyEntrySectionAtoBs = new ArrayList<Zpo01StudyEntrySectionAtoB>();
        Cursor cursorAtoD = crearCursor(Zpo01DBConstants.STUDYENTRY_AB_TABLE, filtro, null, orden);
        if (cursorAtoD != null && cursorAtoD.getCount() > 0) {
            cursorAtoD.moveToFirst();
            studyEntrySectionAtoBs.clear();
            do{
                Zpo01StudyEntrySectionAtoB studyEntrySectionAtoB = null;
                studyEntrySectionAtoB = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionAtoB(cursorAtoD);
                studyEntrySectionAtoBs.add(studyEntrySectionAtoB);
            } while (cursorAtoD.moveToNext());
        }
        if (!cursorAtoD.isClosed()) cursorAtoD.close();
        return studyEntrySectionAtoBs;
    }

    /**
     * Metodos para Zpo01StudyEntrySectionC en la base de datos
     *
     */
    //Crear nuevo Zpo01StudyEntrySectionC en la base de datos
    public void crearZpo01StudyEntrySectionC(Zpo01StudyEntrySectionC studyEntrySectionC) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionCValues(studyEntrySectionC);
        mDb.insert(Zpo01DBConstants.STUDYENTRY_C_TABLE, null, cv);
    }
    //Editar Zpo01StudyEntrySectionC existente en la base de datos
    public boolean editarZpo01StudyEntrySectionC(Zpo01StudyEntrySectionC studyEntrySectionC) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionCValues(studyEntrySectionC);
        return mDb.update(Zpo01DBConstants.STUDYENTRY_C_TABLE, cv, Zpo01DBConstants.recordId + "='"
                + studyEntrySectionC.getRecordId() + "' and " + Zpo01DBConstants.eventName + "='" + studyEntrySectionC.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo01StudyEntrySectionC de la base de datos
    public boolean borrarZpo01StudyEntrySectionC() {
        return mDb.delete(Zpo01DBConstants.STUDYENTRY_C_TABLE, null, null) > 0;
    }
    //Obtener un Zpo01StudyEntrySectionC de la base de datos
    public Zpo01StudyEntrySectionC getZpo01StudyEntrySectionC(String filtro, String orden) throws SQLException {
        Zpo01StudyEntrySectionC studyEntrySectionC = null;
        Cursor cursorE = crearCursor(Zpo01DBConstants.STUDYENTRY_C_TABLE, filtro, null, orden);
        if (cursorE != null && cursorE.getCount() > 0) {
            cursorE.moveToFirst();
            studyEntrySectionC=Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionC(cursorE);
        }
        if (!cursorE.isClosed()) cursorE.close();
        return studyEntrySectionC;
    }
    //Obtener una lista de Zpo01StudyEntrySectionC de la base de datos
    public List<Zpo01StudyEntrySectionC> getZpo01StudyEntrySectionCs(String filtro, String orden) throws SQLException {
        List<Zpo01StudyEntrySectionC> studyEntrySectionCs = new ArrayList<Zpo01StudyEntrySectionC>();
        Cursor cursorE = crearCursor(Zpo01DBConstants.STUDYENTRY_C_TABLE, filtro, null, orden);
        if (cursorE != null && cursorE.getCount() > 0) {
            cursorE.moveToFirst();
            studyEntrySectionCs.clear();
            do{
                Zpo01StudyEntrySectionC studyEntrySectionC = null;
                studyEntrySectionC = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionC(cursorE);
                studyEntrySectionCs.add(studyEntrySectionC);
            } while (cursorE.moveToNext());
        }
        if (!cursorE.isClosed()) cursorE.close();
        return studyEntrySectionCs;
    }

    /**
     * Metodos para Zpo01StudyEntrySectionDtoF en la base de datos
     *
     */
    //Crear nuevo Zpo01StudyEntrySectionDtoF en la base de datos
    public void crearZpo01StudyEntrySectionDtoF(Zpo01StudyEntrySectionDtoF studyEntrySectionDtoF) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionDtoFValues(studyEntrySectionDtoF);
        mDb.insert(Zpo01DBConstants.STUDYENTRY_DF_TABLE, null, cv);
    }
    //Editar Zpo01StudyEntrySectionDtoF existente en la base de datos
    public boolean editarZpo01StudyEntrySectionDtoF(Zpo01StudyEntrySectionDtoF studyEntrySectionDtoF) {
        ContentValues cv = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionDtoFValues(studyEntrySectionDtoF);
        return mDb.update(Zpo01DBConstants.STUDYENTRY_DF_TABLE, cv, Zpo01DBConstants.recordId + "='"
                + studyEntrySectionDtoF.getRecordId() + "' and " + Zpo01DBConstants.eventName + "='" + studyEntrySectionDtoF.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo01StudyEntrySectionDtoF de la base de datos
    public boolean borrarZpo01StudyEntrySectionDtoF() {
        return mDb.delete(Zpo01DBConstants.STUDYENTRY_DF_TABLE, null, null) > 0;
    }
    //Obtener un Zpo01StudyEntrySectionDtoF de la base de datos
    public Zpo01StudyEntrySectionDtoF getZpo01StudyEntrySectionDtoF(String filtro, String orden) throws SQLException {
        Zpo01StudyEntrySectionDtoF studyEntrySectionDtoF = null;
        Cursor cursorFtoK = crearCursor(Zpo01DBConstants.STUDYENTRY_DF_TABLE, filtro, null, orden);
        if (cursorFtoK != null && cursorFtoK.getCount() > 0) {
            cursorFtoK.moveToFirst();
            studyEntrySectionDtoF=Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionDtoF(cursorFtoK);
        }
        if (!cursorFtoK.isClosed()) cursorFtoK.close();
        return studyEntrySectionDtoF;
    }
    //Obtener una lista de Zpo01StudyEntrySectionDtoF de la base de datos
    public List<Zpo01StudyEntrySectionDtoF> getZpo01StudyEntrySectionDtoFs(String filtro, String orden) throws SQLException {
        List<Zpo01StudyEntrySectionDtoF> studyEntrySectionDtoFs = new ArrayList<Zpo01StudyEntrySectionDtoF>();
        Cursor cursorFtoK = crearCursor(Zpo01DBConstants.STUDYENTRY_DF_TABLE, filtro, null, orden);
        if (cursorFtoK != null && cursorFtoK.getCount() > 0) {
            cursorFtoK.moveToFirst();
            studyEntrySectionDtoFs.clear();
            do{
                Zpo01StudyEntrySectionDtoF studyEntrySectionDtoF = null;
                studyEntrySectionDtoF = Zpo01StudyEntryHelper.crearZpo01StudyEntrySectionDtoF(cursorFtoK);
                studyEntrySectionDtoFs.add(studyEntrySectionDtoF);
            } while (cursorFtoK.moveToNext());
        }
        if (!cursorFtoK.isClosed()) cursorFtoK.close();
        return studyEntrySectionDtoFs;
    }

    /**
     * Metodos para Zpo02BiospecimenCollection en la base de datos
     *
     */
    //Crear nuevo Zpo02BiospecimenCollection en la base de datos
    public void crearZpo02BiospecimenCollection(Zpo02BiospecimenCollection biospecimenCollection) {
        ContentValues cv = Zpo02BiospecimenCollectionHelper.crearZpo02BiospecimenCollection(biospecimenCollection);
        mDb.insert(Zpo02DBConstants.BIOCOLLECTION_TABLE, null, cv);
    }
    //Editar Zpo02BiospecimenCollection existente en la base de datos
    public boolean editarZpo02BiospecimenCollection(Zpo02BiospecimenCollection biospecimenCollection) {
        ContentValues cv = Zpo02BiospecimenCollectionHelper.crearZpo02BiospecimenCollection(biospecimenCollection);
        return mDb.update(Zpo02DBConstants.BIOCOLLECTION_TABLE, cv, Zpo02DBConstants.recordId + "='"
                + biospecimenCollection.getRecordId() + "' and " + Zpo02DBConstants.eventName + "='" + biospecimenCollection.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo02BiospecimenCollection de la base de datos
    public boolean borrarZpo02BiospecimenCollection() {
        return mDb.delete(Zpo02DBConstants.BIOCOLLECTION_TABLE, null, null) > 0;
    }
    //Obtener un Zpo02BiospecimenCollection de la base de datos
    public Zpo02BiospecimenCollection getZpo02BiospecimenCollection(String filtro, String orden) throws SQLException {
        Zpo02BiospecimenCollection biospecimenCollection = null;
        Cursor cursorBC = crearCursor(Zpo02DBConstants.BIOCOLLECTION_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            biospecimenCollection=Zpo02BiospecimenCollectionHelper.crearZpo02BiospecimenCollection(cursorBC);
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return biospecimenCollection;
    }
    //Obtener una lista de Zpo02BiospecimenCollection de la base de datos
    public List<Zpo02BiospecimenCollection> getZpo02BiospecimenCollections(String filtro, String orden) throws SQLException {
        List<Zpo02BiospecimenCollection> biospecimenCollections = new ArrayList<Zpo02BiospecimenCollection>();
        Cursor cursorBC = crearCursor(Zpo02DBConstants.BIOCOLLECTION_TABLE, filtro, null, orden);
        if (cursorBC != null && cursorBC.getCount() > 0) {
            cursorBC.moveToFirst();
            biospecimenCollections.clear();
            do{
                Zpo02BiospecimenCollection biospecimenCollection = null;
                biospecimenCollection = Zpo02BiospecimenCollectionHelper.crearZpo02BiospecimenCollection(cursorBC);
                biospecimenCollections.add(biospecimenCollection);
            } while (cursorBC.moveToNext());
        }
        if (!cursorBC.isClosed()) cursorBC.close();
        return biospecimenCollections;
    }

    /**
     * Metodos para Zpo04ExtendedSectionAtoD en la base de datos
     *
     */
    //Crear nuevo Zpo04ExtendedSectionAtoD en la base de datos
    public void crearZpo04ExtendedSectionAtoD(Zpo04ExtendedSectionAtoD extendedSectionAtoD) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionAtoD(extendedSectionAtoD);
        mDb.insert(Zpo04DBConstants.EXTENDED_AD_TABLE, null, cv);
    }
    //Editar Zpo04ExtendedSectionAtoD existente en la base de datos
    public boolean editarZpo04ExtendedSectionAtoD(Zpo04ExtendedSectionAtoD extendedSectionAtoD) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionAtoD(extendedSectionAtoD);
        return mDb.update(Zpo04DBConstants.EXTENDED_AD_TABLE, cv, Zpo04DBConstants.recordId + "='"
                + extendedSectionAtoD.getRecordId() + "' and " + Zpo04DBConstants.eventName + "='" + extendedSectionAtoD.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo04ExtendedSectionAtoD de la base de datos
    public boolean borrarZpo04ExtendedSectionAtoD() {
        return mDb.delete(Zpo04DBConstants.EXTENDED_AD_TABLE, null, null) > 0;
    }
    //Obtener un Zpo04ExtendedSectionAtoD de la base de datos
    public Zpo04ExtendedSectionAtoD getZpo04ExtendedSectionAtoD(String filtro, String orden) throws SQLException {
        Zpo04ExtendedSectionAtoD extendedSectionAtoD = null;
        Cursor cursorAD = crearCursor(Zpo04DBConstants.EXTENDED_AD_TABLE, filtro, null, orden);
        if (cursorAD != null && cursorAD.getCount() > 0) {
            cursorAD.moveToFirst();
            extendedSectionAtoD= Zpo04ExtendedHelper.crearZpo04ExtendedSectionAtoD(cursorAD);
        }
        if (!cursorAD.isClosed()) cursorAD.close();
        return extendedSectionAtoD;
    }
    //Obtener una lista de Zpo04ExtendedSectionAtoD de la base de datos
    public List<Zpo04ExtendedSectionAtoD> getZpo04ExtendedSectionAtoDs(String filtro, String orden) throws SQLException {
        List<Zpo04ExtendedSectionAtoD> extendedSectionAtoDs = new ArrayList<Zpo04ExtendedSectionAtoD>();
        Cursor cursorAD = crearCursor(Zpo04DBConstants.EXTENDED_AD_TABLE, filtro, null, orden);
        if (cursorAD != null && cursorAD.getCount() > 0) {
            cursorAD.moveToFirst();
            extendedSectionAtoDs.clear();
            do{
                Zpo04ExtendedSectionAtoD extendedSectionAtoD = null;
                extendedSectionAtoD = Zpo04ExtendedHelper.crearZpo04ExtendedSectionAtoD(cursorAD);
                extendedSectionAtoDs.add(extendedSectionAtoD);
            } while (cursorAD.moveToNext());
        }
        if (!cursorAD.isClosed()) cursorAD.close();
        return extendedSectionAtoDs;
    }

    /**
     * Metodos para Zpo04ExtendedSectionE en la base de datos
     *
     */
    //Crear nuevo Zpo04ExtendedSectionE en la base de datos
    public void crearZpo04ExtendedSectionE(Zpo04ExtendedSectionE extendedSectionE) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionE(extendedSectionE);
        mDb.insert(Zpo04DBConstants.EXTENDED_E_TABLE, null, cv);
    }
    //Editar Zpo04ExtendedSectionE existente en la base de datos
    public boolean editarZpo04ExtendedSectionE(Zpo04ExtendedSectionE extendedSectionE) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionE(extendedSectionE);
        return mDb.update(Zpo04DBConstants.EXTENDED_E_TABLE, cv, Zpo04DBConstants.recordId + "='"
                + extendedSectionE.getRecordId() + "' and " + Zpo04DBConstants.eventName + "='" + extendedSectionE.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo04ExtendedSectionE de la base de datos
    public boolean borrarZpo04ExtendedSectionE() {
        return mDb.delete(Zpo04DBConstants.EXTENDED_E_TABLE, null, null) > 0;
    }
    //Obtener un Zpo04ExtendedSectionE de la base de datos
    public Zpo04ExtendedSectionE getZpo04ExtendedSectionE(String filtro, String orden) throws SQLException {
        Zpo04ExtendedSectionE extendedSectionE = null;
        Cursor cursorE = crearCursor(Zpo04DBConstants.EXTENDED_E_TABLE, filtro, null, orden);
        if (cursorE != null && cursorE.getCount() > 0) {
            cursorE.moveToFirst();
            extendedSectionE=Zpo04ExtendedHelper.crearZpo04ExtendedSectionE(cursorE);
        }
        if (!cursorE.isClosed()) cursorE.close();
        return extendedSectionE;
    }
    //Obtener una lista de Zpo04ExtendedSectionE de la base de datos
    public List<Zpo04ExtendedSectionE> getZpo04ExtendedSectionEs(String filtro, String orden) throws SQLException {
        List<Zpo04ExtendedSectionE> extendedSectionEs = new ArrayList<Zpo04ExtendedSectionE>();
        Cursor cursorE = crearCursor(Zpo04DBConstants.EXTENDED_E_TABLE, filtro, null, orden);
        if (cursorE != null && cursorE.getCount() > 0) {
            cursorE.moveToFirst();
            extendedSectionEs.clear();
            do{
                Zpo04ExtendedSectionE extendedSectionE = null;
                extendedSectionE = Zpo04ExtendedHelper.crearZpo04ExtendedSectionE(cursorE);
                extendedSectionEs.add(extendedSectionE);
            } while (cursorE.moveToNext());
        }
        if (!cursorE.isClosed()) cursorE.close();
        return extendedSectionEs;
    }

    /**
     * Metodos para Zpo04ExtendedSectionF en la base de datos
     *
     */
    //Crear nuevo Zpo04ExtendedSectionF en la base de datos
    public void crearZpo04ExtendedSectionF(Zpo04ExtendedSectionF extendedSectionF) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionF(extendedSectionF);
        mDb.insert(Zpo04DBConstants.EXTENDED_F_TABLE, null, cv);
    }
    //Editar Zpo04ExtendedSectionF existente en la base de datos
    public boolean editarZpo04ExtendedSectionF(Zpo04ExtendedSectionF extendedSectionF) {
        ContentValues cv = Zpo04ExtendedHelper.crearZpo04ExtendedSectionF(extendedSectionF);
        return mDb.update(Zpo04DBConstants.EXTENDED_F_TABLE, cv, Zpo04DBConstants.recordId + "='"
                + extendedSectionF.getRecordId() + "' and " + Zpo04DBConstants.eventName + "='" + extendedSectionF.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo04ExtendedSectionF de la base de datos
    public boolean borrarZpo04ExtendedSectionF() {
        return mDb.delete(Zpo04DBConstants.EXTENDED_F_TABLE, null, null) > 0;
    }
    //Obtener un Zpo04ExtendedSectionF de la base de datos
    public Zpo04ExtendedSectionF getZpo04ExtendedSectionF(String filtro, String orden) throws SQLException {
        Zpo04ExtendedSectionF extendedSectionF = null;
        Cursor cursorFH = crearCursor(Zpo04DBConstants.EXTENDED_F_TABLE, filtro, null, orden);
        if (cursorFH != null && cursorFH.getCount() > 0) {
            cursorFH.moveToFirst();
            extendedSectionF=Zpo04ExtendedHelper.crearZpo04ExtendedSectionF(cursorFH);
        }
        if (!cursorFH.isClosed()) cursorFH.close();
        return extendedSectionF;
    }
    //Obtener una lista de Zpo04ExtendedSectionF de la base de datos
    public List<Zpo04ExtendedSectionF> getZpo04ExtendedSectionFs(String filtro, String orden) throws SQLException {
        List<Zpo04ExtendedSectionF> extendedSectionFs = new ArrayList<Zpo04ExtendedSectionF>();
        Cursor cursorFH = crearCursor(Zpo04DBConstants.EXTENDED_F_TABLE, filtro, null, orden);
        if (cursorFH != null && cursorFH.getCount() > 0) {
            cursorFH.moveToFirst();
            extendedSectionFs.clear();
            do{
                Zpo04ExtendedSectionF extendedSectionF = null;
                extendedSectionF = Zpo04ExtendedHelper.crearZpo04ExtendedSectionF(cursorFH);
                extendedSectionFs.add(extendedSectionF);
            } while (cursorFH.moveToNext());
        }
        if (!cursorFH.isClosed()) cursorFH.close();
        return extendedSectionFs;
    }

    /**
     * Metodos para Zpo05Delivery en la base de datos
     *
     */
    //Crear nuevo Zpo05Delivery en la base de datos
    public void crearZpo05Delivery(Zpo05Delivery deliveryAnd6weekVisit) {
        ContentValues cv = Zpo05DeliveryHelper.crearZpo05Delivery(deliveryAnd6weekVisit);
        mDb.insert(Zpo05DBConstants.DELIVERY_TABLE, null, cv);
    }
    //Editar Zpo05Delivery existente en la base de datos
    public boolean editarZpo05Delivery(Zpo05Delivery deliveryAnd6weekVisit) {
        ContentValues cv = Zpo05DeliveryHelper.crearZpo05Delivery(deliveryAnd6weekVisit);
        return mDb.update(Zpo05DBConstants.DELIVERY_TABLE, cv, Zpo05DBConstants.recordId + "='"
                + deliveryAnd6weekVisit.getRecordId() + "' and " + Zpo05DBConstants.eventName + "='" + deliveryAnd6weekVisit.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo05Delivery de la base de datos
    public boolean borrarZpo05Delivery() {
        return mDb.delete(Zpo05DBConstants.DELIVERY_TABLE, null, null) > 0;
    }
    //Obtener un Zpo05Delivery de la base de datos
    public Zpo05Delivery getZpo05Delivery(String filtro, String orden) throws SQLException {
        Zpo05Delivery deliveryAnd6weekVisit = null;
        Cursor cursor = crearCursor(Zpo05DBConstants.DELIVERY_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            deliveryAnd6weekVisit=Zpo05DeliveryHelper.crearZpo05Delivery(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return deliveryAnd6weekVisit;
    }
    //Obtener una lista de Zpo05Delivery de la base de datos
    public List<Zpo05Delivery> getZpo05Deliverys(String filtro, String orden) throws SQLException {
        List<Zpo05Delivery> deliveryAnd6weekVisits = new ArrayList<Zpo05Delivery>();
        Cursor cursor = crearCursor(Zpo05DBConstants.DELIVERY_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            deliveryAnd6weekVisits.clear();
            do{
                Zpo05Delivery deliveryAnd6weekVisit = null;
                deliveryAnd6weekVisit = Zpo05DeliveryHelper.crearZpo05Delivery(cursor);
                deliveryAnd6weekVisits.add(deliveryAnd6weekVisit);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return deliveryAnd6weekVisits;
    }

    
    /**
     * Metodos para Zpo08StudyExit en la base de datos
     *
     */
    //Crear nuevo Zpo08StudyExit en la base de datos
    public void crearZpo08StudyExit(Zpo08StudyExit studyExit) {
        ContentValues cv = Zpo08StudyExitHelper.crearZpo08StudyExit(studyExit);
        mDb.insert(Zpo08DBConstants.STUDYEXIT_TABLE, null, cv);
    }
    //Editar Zpo08StudyExit existente en la base de datos
    public boolean editarZpo08StudyExit(Zpo08StudyExit studyExit) {
        ContentValues cv = Zpo08StudyExitHelper.crearZpo08StudyExit(studyExit);
        return mDb.update(Zpo08DBConstants.STUDYEXIT_TABLE, cv, Zpo08DBConstants.recordId + "='"
                + studyExit.getRecordId() + "' and " + Zpo08DBConstants.eventName + "='" + studyExit.getEventName() +"'", null) > 0;
    }
    //Limpiar la tabla de Zpo08StudyExit de la base de datos
    public boolean borrarZpo08StudyExit() {
        return mDb.delete(Zpo08DBConstants.STUDYEXIT_TABLE, null, null) > 0;
    }
    //Obtener un Zpo08StudyExit de la base de datos
    public Zpo08StudyExit getZpo08StudyExit(String filtro, String orden) throws SQLException {
        Zpo08StudyExit studyExit = null;
        Cursor cursor = crearCursor(Zpo08DBConstants.STUDYEXIT_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            studyExit=Zpo08StudyExitHelper.crearZpo08StudyExit(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return studyExit;
    }
    //Obtener una lista de Zpo08StudyExit de la base de datos
    public List<Zpo08StudyExit> getZpo08StudyExits(String filtro, String orden) throws SQLException {
        List<Zpo08StudyExit> zpo08StudyExits = new ArrayList<Zpo08StudyExit>();
        Cursor cursor = crearCursor(Zpo08DBConstants.STUDYEXIT_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            zpo08StudyExits.clear();
            do{
                Zpo08StudyExit studyExit = null;
                studyExit = Zpo08StudyExitHelper.crearZpo08StudyExit(cursor);
                zpo08StudyExits.add(studyExit);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return zpo08StudyExits;
    }

    /**
     * Metodos para ZpoEstadoMadre en la base de datos
     *
     * @param estado
     *            Objeto ZpoEstadoMadre que contiene la informacion
     *
     */
    //Crear nuevo ZpoEstadoMadre en la base de datos
    public void crearZpoEstadoMadre (ZpoEstadoEmbarazada estado) {
        ContentValues cv = ZpoEstadoMadreHelper.crearZpoEstadoMadreValues(estado);
        mDb.insert(MainDBConstants.STATUS_MOTHER_TABLE, null, cv);
    }
    //Editar ZpoEstadoMadre existente en la base de datos
    public boolean editarZpoEstadoMadre (ZpoEstadoEmbarazada estado) {
        ContentValues cv = ZpoEstadoMadreHelper.crearZpoEstadoMadreValues(estado);
        return mDb.update(MainDBConstants.STATUS_MOTHER_TABLE, cv, MainDBConstants.recordId + "='"
                + estado.getRecordId()+"'", null) > 0;
    }
    //Limpiar la tabla de ZpoEstadoMadre de la base de datos
    public boolean borrarZpoEstadoMadre () {
        return mDb.delete(MainDBConstants.STATUS_MOTHER_TABLE, null, null) > 0;
    }
    //Obtener un ZpoEstadoMadre de la base de datos
    public ZpoEstadoEmbarazada getZpoEstadoMadre (String filtro, String orden) throws SQLException {
        ZpoEstadoEmbarazada mEstado = null;
        Cursor cursorEstado = crearCursor(MainDBConstants.STATUS_MOTHER_TABLE, filtro, null, orden);
        if (cursorEstado != null && cursorEstado.getCount() > 0) {
            cursorEstado.moveToFirst();
            mEstado=ZpoEstadoMadreHelper.crearZpoEstadoMadre(cursorEstado);
        }
        if (!cursorEstado.isClosed()) cursorEstado.close();
        return mEstado;
    }

    //Obtener una lista de ZpoEstadoMadre de la base de datos
    public List<ZpoEstadoEmbarazada> getZpoEstadoMadres(String filtro, String orden) throws SQLException {
        List<ZpoEstadoEmbarazada> zpEstadoEmbarazadas = new ArrayList<ZpoEstadoEmbarazada>();
        Cursor cursorStatus = crearCursor(MainDBConstants.STATUS_MOTHER_TABLE, filtro, null, orden);
        if (cursorStatus != null && cursorStatus.getCount() > 0) {
            cursorStatus.moveToFirst();
            zpEstadoEmbarazadas.clear();
            do{
                ZpoEstadoEmbarazada estadoEmbarazada = null;
                estadoEmbarazada = ZpoEstadoMadreHelper.crearZpoEstadoMadre(cursorStatus);
                zpEstadoEmbarazadas.add(estadoEmbarazada);
            } while (cursorStatus.moveToNext());
        }
        if (!cursorStatus.isClosed()) cursorStatus.close();
        return zpEstadoEmbarazadas;
    }

    /**
     * Metodos para ZpoInfantData en la base de datos
     *
     */
    //Crear nuevo ZpoInfantData en la base de datos
    public void crearZpoInfantData(ZpoInfantData mZpoInfantData) {
        ContentValues cv = ZpoInfantDataHelper.crearZpoInfantData(mZpoInfantData);
        mDb.insert(MainDBConstants.INFANTDATA_TABLE, null, cv);
    }
    //Editar ZpoInfantData existente en la base de datos
    public boolean editarZpoInfantData(ZpoInfantData mZpoInfantData) {
        ContentValues cv = ZpoInfantDataHelper.crearZpoInfantData(mZpoInfantData);
        return mDb.update(MainDBConstants.INFANTDATA_TABLE, cv, MainDBConstants.recordId + "='"
                + mZpoInfantData.getRecordId() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoInfantData de la base de datos
    public boolean borrarZpoInfantData() {
        return mDb.delete(MainDBConstants.INFANTDATA_TABLE, null, null) > 0;
    }
    //Obtener un ZpoInfantData de la base de datos
    public ZpoInfantData getZpoInfantData(String filtro, String orden) throws SQLException {
        ZpoInfantData mZpoInfantData = null;
        Cursor cursor = crearCursor(MainDBConstants.INFANTDATA_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoInfantData=ZpoInfantDataHelper.crearZpoInfantData(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoInfantData;
    }
    //Obtener una lista de ZpoInfantData de la base de datos
    public List<ZpoInfantData> getZpoInfantDatas(String filtro, String orden) throws SQLException {
        List<ZpoInfantData> mZpoInfantDatas = new ArrayList<ZpoInfantData>();
        Cursor cursor = crearCursor(MainDBConstants.INFANTDATA_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoInfantDatas.clear();
            do{
                ZpoInfantData mZpoInfantData = null;
                mZpoInfantData = ZpoInfantDataHelper.crearZpoInfantData(cursor);
                mZpoInfantDatas.add(mZpoInfantData);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoInfantDatas;
    }

    /**
     * Metodos para ZpoEstadoInfante en la base de datos
     *
     */
    //Crear nuevo ZpoEstadoInfante en la base de datos
    public void crearZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante) {
        ContentValues cv = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(mZpoEstadoInfante);
        mDb.insert(MainDBConstants.INFANTSTATUS_TABLE, null, cv);
    }
    //Editar ZpoEstadoInfante existente en la base de datos
    public boolean editarZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante) {
        ContentValues cv = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(mZpoEstadoInfante);
        return mDb.update(MainDBConstants.INFANTSTATUS_TABLE, cv, MainDBConstants.recordId + "='"
                + mZpoEstadoInfante.getRecordId() +"'", null) > 0;
    }
    //Limpiar la tabla de ZpoEstadoInfante de la base de datos
    public boolean borrarZpoEstadoInfante() {
        return mDb.delete(MainDBConstants.INFANTSTATUS_TABLE, null, null) > 0;
    }
    //Obtener un ZpoEstadoInfante de la base de datos
    public ZpoEstadoInfante getZpoEstadoInfante(String filtro, String orden) throws SQLException {
        ZpoEstadoInfante mZpoEstadoInfante = null;
        Cursor cursor = crearCursor(MainDBConstants.INFANTSTATUS_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoEstadoInfante=ZpoEstadoInfanteHelper.crearZpoEstadoInfante(cursor);
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoEstadoInfante;
    }
    //Obtener una lista de ZpoEstadoInfante de la base de datos
    public List<ZpoEstadoInfante> getZpoEstadoInfantes(String filtro, String orden) throws SQLException {
        List<ZpoEstadoInfante> mZpoEstadoInfantes = new ArrayList<ZpoEstadoInfante>();
        Cursor cursor = crearCursor(MainDBConstants.INFANTSTATUS_TABLE, filtro, null, orden);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            mZpoEstadoInfantes.clear();
            do{
                ZpoEstadoInfante mZpoEstadoInfanteData = null;
                mZpoEstadoInfanteData = ZpoEstadoInfanteHelper.crearZpoEstadoInfante(cursor);
                mZpoEstadoInfantes.add(mZpoEstadoInfanteData);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) cursor.close();
        return mZpoEstadoInfantes;
    }
}
