package ni.org.ics.zpo.appmovil.adapters.eventosinfante;

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
import ni.org.ics.zpo.domain.*;

public class InfantVisitAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	private final Zpo02BiospecimenCollection mZp02d;
	private final Zpo07InfantAssessmentVisit mZpo07;
	private final Zpo07aInfantOphtResults mZpo07a;
	private final Zpo07bInfantAudioResults mZpo07b;
	private final Zpo07cInfantImageStudies mZpo07c;
	private final Zpo07dInfantBayleyScales mZpo07d;
	
	public InfantVisitAdapter(Context context, int textViewResourceId,
                              String[] values, Zpo02BiospecimenCollection zp02, Zpo07InfantAssessmentVisit zp07, Zpo07aInfantOphtResults zp07a, Zpo07bInfantAudioResults zp07b, Zpo07cInfantImageStudies zp07c, Zpo07dInfantBayleyScales zp07d) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
		this.mZp02d = zp02;
		this.mZpo07 = zp07;
		this.mZpo07a = zp07a;
		this.mZpo07b = zp07b;
		this.mZpo07c = zp07c;
		this.mZpo07d = zp07d;
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
		textView.setTextColor(Color.BLACK);
		textView.setText(values[position]);
		
		// Change icon based on position
		Drawable img = null;
		switch (position){

		case 0:
			if(mZpo07!=null){
				if (mZpo07.getPart1() != null){
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
				}else{
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
				}

			}
			else{
				textView.setTextColor(Color.RED);
				textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
			}
			img=getContext().getResources().getDrawable( R.drawable.ic_monthly);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			break;
			case 1:
				if(mZpo07!=null){
					if(mZpo07.getPart2() != null){
						textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
					}else{
						textView.setTextColor(Color.RED);
						textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
					}

				}
				else{
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
				}
				img=getContext().getResources().getDrawable( R.drawable.ic_monthly);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
			case 2:
				if(mZpo07!=null){
					if (mZpo07.getPart3() != null){
						textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
					}else{
						textView.setTextColor(Color.RED);
						textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
					}
									}
				else{
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
				}
				img=getContext().getResources().getDrawable( R.drawable.ic_monthly);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
			case 3:
				if(mZp02d!=null){
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
				}
				else{
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
				}
				img=getContext().getResources().getDrawable( R.drawable.ic_sample);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
			case 4:
				if (mZpo07a != null) {
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
				} else {
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
				}
				img = getContext().getResources().getDrawable(R.drawable.ic_opht);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
			case 5:
				if (mZpo07b != null) {
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
				} else {
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
				}
				img = getContext().getResources().getDrawable(R.drawable.ic_audio);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;

			case 6:
				if (mZpo07c != null) {
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
				} else {
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
				}
				img = getContext().getResources().getDrawable(R.drawable.ic_image);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
			case 7:
				if (mZpo07d != null) {
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
				} else {
					textView.setTextColor(Color.RED);
					textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
				}
				img = getContext().getResources().getDrawable(R.drawable.ic_bayley);
				textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
				break;
		default:
			img=getContext().getResources().getDrawable( R.drawable.ic_launcher);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
			break;
		}

		return v;
	}
}
