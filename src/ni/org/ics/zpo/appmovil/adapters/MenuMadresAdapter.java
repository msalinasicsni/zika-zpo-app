package ni.org.ics.zpo.appmovil.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.domain.Zpo00Screening;
import ni.org.ics.zpo.domain.Zpo05Delivery;
import ni.org.ics.zpo.domain.Zpo08StudyExit;
import ni.org.ics.zpo.domain.ZpoEstadoEmbarazada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MenuMadresAdapter extends ArrayAdapter<String> {

	private final String[] values;
	private final Zpo00Screening mZp00;
	private final ZpoEstadoEmbarazada mZpEstado;
	private final Zpo08StudyExit mZpSalida;
    private final Zpo05Delivery mZpo05;
    private final Calendar fechaIngreso;
	private final Context context;
	private Date fechaEvento;
	private Date todayDate;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public MenuMadresAdapter(Context context, int textViewResourceId,
                             String[] values, Zpo00Screening zp00, ZpoEstadoEmbarazada zpEstado, Zpo08StudyExit zpSalida, Zpo05Delivery zpo05) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
		this.mZp00 = zp00;
		this.mZpEstado = zpEstado;
		this.mZpSalida = zpSalida;
        this.mZpo05 = zpo05;
		try {
			this.todayDate = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fechaIngreso = Calendar.getInstance();
        if (mZpo05!=null)
            fechaIngreso.setTime(mZpo05.getDeliDeliveryDate());
	}
	
	


	
	@Override
    public boolean isEnabled(int position) {
        // Disable the first item of GridView
		boolean habilitado = true;
		if(mZpSalida!= null){
			return false;
		}
        return habilitado;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.menu_item_2, null);
		}
		TextView textView = (TextView) v.findViewById(R.id.label);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setText(values[position]);
		// Change icon based on position
		Drawable img = null;
		switch (position){
		case 0:
			fechaEvento = fechaIngreso.getTime();
			if(String.valueOf(mZpEstado.getIngreso()).equals("0")){
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                if (mZpo05!=null) {
                    long dif = getDateDiff(fechaEvento, todayDate, TimeUnit.DAYS);
                    if (dif > 15) {
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    } else if (dif < 1) {
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.ontime));
                    } else {
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    }
                }
			}
			else{
				textView.setTextColor(Color.BLACK);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
			}
			img=getContext().getResources().getDrawable( R.drawable.ic_enroll);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			break;
		case 1:
			fechaIngreso.add(Calendar.DATE, 365);
			fechaEvento = fechaIngreso.getTime();
			if(String.valueOf(mZpEstado.getMes12()).equals("0")){
                textView.setTextColor(Color.BLUE);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                if (mZpo05!=null) {
                    long dif = getDateDiff(fechaEvento, todayDate, TimeUnit.DAYS);
                    if (dif < -7) {
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.programmed) + ": " + formatter.format(fechaEvento));
                    } else if (dif > 7) {
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    } else if (dif <= 0) {
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.ontime));
                    } else {
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    }
                }
			}
			else{
				textView.setTextColor(Color.BLACK);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
			}
			img=getContext().getResources().getDrawable( R.drawable.ic_visitacasa);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			fechaIngreso.add(Calendar.DATE, -365);
			break;
		case 2: 
			fechaIngreso.add(Calendar.DATE, 730);
			fechaEvento = fechaIngreso.getTime();
			if(String.valueOf(mZpEstado.getMes24()).equals("0")){
                textView.setTextColor(Color.BLUE);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                if (mZpo05!=null) {
                    long dif = getDateDiff(fechaEvento, todayDate, TimeUnit.DAYS);
                    if (dif < -7) {
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.programmed) + ": " + formatter.format(fechaEvento));
                    } else if (dif > 7) {
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    } else if (dif <= 0) {
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.ontime));
                    } else {
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.delayed));
                    }
                }
			}
			else{
				textView.setTextColor(Color.BLACK);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
			}
			img=getContext().getResources().getDrawable( R.drawable.ic_visitacasa);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			fechaIngreso.add(Calendar.DATE, -730);
			break;
		default:
			img=getContext().getResources().getDrawable( R.drawable.logo);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			break;
		}

		return v;
	}

	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
