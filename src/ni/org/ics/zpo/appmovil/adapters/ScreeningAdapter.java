package ni.org.ics.zpo.appmovil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.domain.Zpo00Screening;

import java.text.SimpleDateFormat;
import java.util.List;

public class ScreeningAdapter extends ArrayAdapter<Zpo00Screening> {

	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");
	
	public ScreeningAdapter(Context context, int textViewResourceId,
                            List<Zpo00Screening> items) {
		super(context, textViewResourceId, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.complex_list_item, null);
		}
		Zpo00Screening p = getItem(position);
		if (p != null) {

			TextView textView = (TextView) v.findViewById(R.id.name_text);
			if (textView != null) {
				textView.setText(this.getContext().getString(R.string.study_id) + ": " + p.getRecordId());
			}

			textView = (TextView) v.findViewById(R.id.identifier_text);
			if (textView != null) {
				textView.setText(this.getContext().getString(R.string.mat_fec) + ": " + mDateFormat.format(p.getScrVisitDate()));
			}
			
			textView = (TextView) v.findViewById(R.id.infoc_text);

			ImageView imageView = (ImageView) v.findViewById(R.id.image);
			if (imageView != null) {
				imageView.setImageResource(R.drawable.ic_pregnant);
			}
		}
		return v;
	}
}
