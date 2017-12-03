package cl.mypantry.Libraries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cl.mypantry.Models.PantryProduct;
import cl.mypantry.R;

public class PantryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<PantryProduct> pantryProductList;

    public PantryAdapter(Context context, int layout, List<PantryProduct> pantryProductList) {
        this.context = context;
        this.layout = layout;
        this.pantryProductList = pantryProductList;
    }

    @Override
    public int getCount() {
        return this.pantryProductList.size();
    }

    @Override
    public PantryProduct getItem(int position) {
        return this.pantryProductList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewProduct productItem;

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            productItem = new ViewProduct();
            productItem.name = (TextView) convertView.findViewById(R.id.textViewProductName);
            productItem.brand = (TextView) convertView.findViewById(R.id.textViewProductBrand);
            productItem.quality = (TextView) convertView.findViewById(R.id.textViewProductQuality);
            productItem.expiration_date = (TextView) convertView.findViewById(R.id.textViewProductExpirationDate);
            convertView.setTag(productItem);
        } else {
            productItem = (ViewProduct) convertView.getTag();
        }

        final PantryProduct currentPantryProduct = getItem(position);
        productItem.name.setText(currentPantryProduct.getName());
        productItem.brand.setText(currentPantryProduct.getBrand());
        productItem.quality.setText(String.valueOf(currentPantryProduct.getQuality()));
        productItem.expiration_date.setText(currentPantryProduct.getExpirationDate().toString());

        return convertView;
    }

    static class ViewProduct {
        private TextView name;
        private TextView brand;
        private TextView quality;
        private TextView expiration_date;
    }
}
