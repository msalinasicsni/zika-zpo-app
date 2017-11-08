package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.Zpo07dInfantBayleyScales;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo07dDBConstants;

import java.util.Date;

/**
 * Created by ics on 22/6/2017.
 */
public class Zpo07dInfantBayleyScalesHelper {

    public static ContentValues crearZpo07dInfantBayleyScales(Zpo07dInfantBayleyScales zp07dInfantBayleyScales){
        ContentValues cv = new ContentValues();
        cv.put(Zpo07dDBConstants.recordId, zp07dInfantBayleyScales.getRecordId());
        cv.put(Zpo07dDBConstants.eventName, zp07dInfantBayleyScales.getEventName());
        if (zp07dInfantBayleyScales.getInfantVisitdt()!=null) cv.put(Zpo07dDBConstants.infantVisitdt, zp07dInfantBayleyScales.getInfantVisitdt().getTime());
        cv.put(Zpo07dDBConstants.infantDone, zp07dInfantBayleyScales.getInfantDone());
        cv.put(Zpo07dDBConstants.infantReaNot, zp07dInfantBayleyScales.getInfantReaNot());
        cv.put(Zpo07dDBConstants.infantNreaOther, zp07dInfantBayleyScales.getInfantNreaOther());
        if (zp07dInfantBayleyScales.getInfantPerfdt()!=null) cv.put(Zpo07dDBConstants.infantPerfdt, zp07dInfantBayleyScales.getInfantPerfdt().getTime());
        cv.put(Zpo07dDBConstants.infantEnglish, zp07dInfantBayleyScales.getInfantEnglish());
        cv.put(Zpo07dDBConstants.infantPrilanguage, zp07dInfantBayleyScales.getInfantPrilanguage());
        cv.put(Zpo07dDBConstants.infantParentlan, zp07dInfantBayleyScales.getInfantParentlan());
        cv.put(Zpo07dDBConstants.infantBayenglish, zp07dInfantBayleyScales.getInfantBayenglish());
        cv.put(Zpo07dDBConstants.infantMed, zp07dInfantBayleyScales.getInfantMed());
        cv.put(Zpo07dDBConstants.infantMedDay, zp07dInfantBayleyScales.getInfantMedDay());
        cv.put(Zpo07dDBConstants.infantTypMed, zp07dInfantBayleyScales.getInfantTypMed());
        cv.put(Zpo07dDBConstants.infantCoguAttem, zp07dInfantBayleyScales.getInfantCoguAttem());
        cv.put(Zpo07dDBConstants.infantCograScore, zp07dInfantBayleyScales.getInfantCograScore());
        cv.put(Zpo07dDBConstants.infantCogscScore, zp07dInfantBayleyScales.getInfantCogscScore());
        cv.put(Zpo07dDBConstants.infantCogcoScore, zp07dInfantBayleyScales.getInfantCogcoScore());
        cv.put(Zpo07dDBConstants.infantCogValid, zp07dInfantBayleyScales.getInfantCogValid());
        cv.put(Zpo07dDBConstants.infantReaInvali, zp07dInfantBayleyScales.getInfantReaInvali());
        cv.put(Zpo07dDBConstants.infantInvaOther, zp07dInfantBayleyScales.getInfantInvaOther());
        cv.put(Zpo07dDBConstants.infantResAtte, zp07dInfantBayleyScales.getInfantResAtte());
        cv.put(Zpo07dDBConstants.infantRetoScore, zp07dInfantBayleyScales.getInfantRetoScore());
        cv.put(Zpo07dDBConstants.infantRescScore, zp07dInfantBayleyScales.getInfantRescScore());
        cv.put(Zpo07dDBConstants.infantExsuAtte, zp07dInfantBayleyScales.getInfantExsuAtte());
        cv.put(Zpo07dDBConstants.infantExtoScore, zp07dInfantBayleyScales.getInfantExtoScore());
        cv.put(Zpo07dDBConstants.infantExscScore, zp07dInfantBayleyScales.getInfantExscScore());
        cv.put(Zpo07dDBConstants.infantSuScore, zp07dInfantBayleyScales.getInfantSuScore());
        cv.put(Zpo07dDBConstants.infantSucomScore, zp07dInfantBayleyScales.getInfantSucomScore());
        cv.put(Zpo07dDBConstants.infantLangValid, zp07dInfantBayleyScales.getInfantLangValid());
        cv.put(Zpo07dDBConstants.infantRelanInvalid, zp07dInfantBayleyScales.getInfantRelanInvalid());
        cv.put(Zpo07dDBConstants.infantRelanOther, zp07dInfantBayleyScales.getInfantRelanOther());
        cv.put(Zpo07dDBConstants.infantFmsAtte, zp07dInfantBayleyScales.getInfantFmsAtte());
        cv.put(Zpo07dDBConstants.infantFmtoScore, zp07dInfantBayleyScales.getInfantFmtoScore());
        cv.put(Zpo07dDBConstants.infantFmscScore, zp07dInfantBayleyScales.getInfantFmscScore());
        cv.put(Zpo07dDBConstants.infantGmsuattm, zp07dInfantBayleyScales.getInfantGmsuattm());
        cv.put(Zpo07dDBConstants.infantGmtoScore, zp07dInfantBayleyScales.getInfantGmtoScore());
        cv.put(Zpo07dDBConstants.infantGmscScore, zp07dInfantBayleyScales.getInfantGmscScore());
        cv.put(Zpo07dDBConstants.infantMssuScore, zp07dInfantBayleyScales.getInfantMssuScore());
        cv.put(Zpo07dDBConstants.infantMscoscore, zp07dInfantBayleyScales.getInfantMscoscore());
        cv.put(Zpo07dDBConstants.infantMtValid, zp07dInfantBayleyScales.getInfantMtValid());
        cv.put(Zpo07dDBConstants.infantMtInvalid, zp07dInfantBayleyScales.getInfantMtInvalid());
        cv.put(Zpo07dDBConstants.infantMtinvOther, zp07dInfantBayleyScales.getInfantMtinvOther());
        cv.put(Zpo07dDBConstants.infantSesAtte, zp07dInfantBayleyScales.getInfantSesAtte());
        cv.put(Zpo07dDBConstants.infantSetoSore, zp07dInfantBayleyScales.getInfantSetoSore());
        cv.put(Zpo07dDBConstants.infantSescScre, zp07dInfantBayleyScales.getInfantSescScre());
        cv.put(Zpo07dDBConstants.infantSecoScre, zp07dInfantBayleyScales.getInfantSecoScre());
        cv.put(Zpo07dDBConstants.infantSemoValid, zp07dInfantBayleyScales.getInfantSemoValid());
        cv.put(Zpo07dDBConstants.infantSemoInvalid, zp07dInfantBayleyScales.getInfantSemoInvalid());
        cv.put(Zpo07dDBConstants.infantSemoinvOther, zp07dInfantBayleyScales.getInfantSemoinvOther());
        cv.put(Zpo07dDBConstants.infantCog76, zp07dInfantBayleyScales.getInfantCog76());
        cv.put(Zpo07dDBConstants.infantCircuEvalu, zp07dInfantBayleyScales.getInfantCircuEvalu());
        cv.put(Zpo07dDBConstants.infantExplain, zp07dInfantBayleyScales.getInfantExplain());
        cv.put(Zpo07dDBConstants.infantBaidCom, zp07dInfantBayleyScales.getInfantBaidCom());
        if (zp07dInfantBayleyScales.getInfantBadtCom()!=null) cv.put(Zpo07dDBConstants.infantBadtCom, zp07dInfantBayleyScales.getInfantBadtCom().getTime());
        cv.put(Zpo07dDBConstants.infantBaeyeIdRevi, zp07dInfantBayleyScales.getInfantBaeyeIdRevi());
        if (zp07dInfantBayleyScales.getInfantBadtRevi()!=null) cv.put(Zpo07dDBConstants.infantBadtRevi, zp07dInfantBayleyScales.getInfantBadtRevi().getTime());
        cv.put(Zpo07dDBConstants.infantBaidEntry, zp07dInfantBayleyScales.getInfantBaidEntry());
        if (zp07dInfantBayleyScales.getInfantBadtEnt()!=null) cv.put(Zpo07dDBConstants.infantBadtEnt, zp07dInfantBayleyScales.getInfantBadtEnt().getTime());


        if (zp07dInfantBayleyScales.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zp07dInfantBayleyScales.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zp07dInfantBayleyScales.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zp07dInfantBayleyScales.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zp07dInfantBayleyScales.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zp07dInfantBayleyScales.getInstancePath());
        cv.put(MainDBConstants.STATUS, zp07dInfantBayleyScales.getEstado());
        cv.put(MainDBConstants.START, zp07dInfantBayleyScales.getStart());
        cv.put(MainDBConstants.END, zp07dInfantBayleyScales.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zp07dInfantBayleyScales.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zp07dInfantBayleyScales.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zp07dInfantBayleyScales.getPhonenumber());
        if (zp07dInfantBayleyScales.getToday() != null) cv.put(MainDBConstants.TODAY, zp07dInfantBayleyScales.getToday().getTime());
        return cv;
    }

    public static Zpo07dInfantBayleyScales crearZpo07dInfantBayleyScales(Cursor cursorIA){
        Zpo07dInfantBayleyScales dInfantBayleySc = new Zpo07dInfantBayleyScales();
        dInfantBayleySc.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.recordId)));
        dInfantBayleySc.setEventName(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.eventName)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantVisitdt))>0) dInfantBayleySc.setInfantVisitdt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantVisitdt))));
        dInfantBayleySc.setInfantDone(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantDone)));
        dInfantBayleySc.setInfantReaNot(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantReaNot)));
        dInfantBayleySc.setInfantNreaOther(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantNreaOther)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantPerfdt))>0) dInfantBayleySc.setInfantPerfdt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantPerfdt))));
        dInfantBayleySc.setInfantEnglish(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantEnglish)));
        dInfantBayleySc.setInfantPrilanguage(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantPrilanguage)));
        dInfantBayleySc.setInfantParentlan(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantParentlan)));
        dInfantBayleySc.setInfantBayenglish(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBayenglish)));
        dInfantBayleySc.setInfantMed(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMed)));
        dInfantBayleySc.setInfantMedDay(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMedDay)));
        dInfantBayleySc.setInfantTypMed(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantTypMed)));
        dInfantBayleySc.setInfantCoguAttem(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCoguAttem)));
        dInfantBayleySc.setInfantCograScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCograScore)));
        dInfantBayleySc.setInfantCogscScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCogscScore)));
        dInfantBayleySc.setInfantCogcoScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCogcoScore)));
        dInfantBayleySc.setInfantCogValid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCogValid)));
        dInfantBayleySc.setInfantReaInvali(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantReaInvali)));
        dInfantBayleySc.setInfantInvaOther(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantInvaOther)));
        dInfantBayleySc.setInfantResAtte(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantResAtte)));
        dInfantBayleySc.setInfantRetoScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantRetoScore)));
        dInfantBayleySc.setInfantRescScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantRescScore)));
        dInfantBayleySc.setInfantExsuAtte(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantExsuAtte)));
        dInfantBayleySc.setInfantExtoScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantExtoScore)));
        dInfantBayleySc.setInfantExscScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantExscScore)));
        dInfantBayleySc.setInfantSuScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSuScore)));
        dInfantBayleySc.setInfantSucomScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSucomScore)));
        dInfantBayleySc.setInfantLangValid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantLangValid)));
        dInfantBayleySc.setInfantRelanInvalid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantRelanInvalid)));
        dInfantBayleySc.setInfantRelanOther(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantRelanOther)));
        dInfantBayleySc.setInfantFmsAtte(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantFmsAtte)));
        dInfantBayleySc.setInfantFmtoScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantFmtoScore)));
        dInfantBayleySc.setInfantFmscScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantFmscScore)));
        dInfantBayleySc.setInfantGmsuattm(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantGmsuattm)));
        dInfantBayleySc.setInfantGmtoScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantGmtoScore)));
        dInfantBayleySc.setInfantGmscScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantGmscScore)));
        dInfantBayleySc.setInfantMssuScore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMssuScore)));
        dInfantBayleySc.setInfantMscoscore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMscoscore)));
        dInfantBayleySc.setInfantMtValid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMtValid)));
        dInfantBayleySc.setInfantMtInvalid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMtInvalid)));
        dInfantBayleySc.setInfantMtinvOther(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantMtinvOther)));
        dInfantBayleySc.setInfantSesAtte(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSesAtte)));
        dInfantBayleySc.setInfantSetoSore(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSetoSore)));
        dInfantBayleySc.setInfantSescScre(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSescScre)));
        dInfantBayleySc.setInfantSecoScre(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSecoScre)));
        dInfantBayleySc.setInfantSemoValid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSemoValid)));
        dInfantBayleySc.setInfantSemoInvalid(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSemoInvalid)));
        dInfantBayleySc.setInfantSemoinvOther(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantSemoinvOther)));
        dInfantBayleySc.setInfantCog76(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCog76)));
        dInfantBayleySc.setInfantCircuEvalu(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantCircuEvalu)));
        dInfantBayleySc.setInfantExplain(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantExplain)));
        dInfantBayleySc.setInfantBaidCom(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBaidCom)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtCom))>0) dInfantBayleySc.setInfantBadtCom(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtCom))));
        dInfantBayleySc.setInfantBaeyeIdRevi(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBaeyeIdRevi)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtRevi))>0) dInfantBayleySc.setInfantBadtRevi(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtRevi))));
        dInfantBayleySc.setInfantBaidEntry(cursorIA.getString(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBaidEntry)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtEnt))>0) dInfantBayleySc.setInfantBadtEnt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07dDBConstants.infantBadtEnt))));


        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))>0) dInfantBayleySc.setRecordDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))));
        dInfantBayleySc.setRecordUser(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.recordUser)));
        dInfantBayleySc.setPasive(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        dInfantBayleySc.setIdInstancia(cursorIA.getInt(cursorIA.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        dInfantBayleySc.setInstancePath(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.FILE_PATH)));
        dInfantBayleySc.setEstado(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.STATUS)));
        dInfantBayleySc.setStart(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.START)));
        dInfantBayleySc.setEnd(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.END)));
        dInfantBayleySc.setSimserial(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        dInfantBayleySc.setPhonenumber(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        dInfantBayleySc.setDeviceid(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))>0) dInfantBayleySc.setToday(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))));

        return dInfantBayleySc;
    }
}
