package adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interactive_programing_assignment.R;

import java.util.ArrayList;

import student_model.Product;

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {
    ArrayList<Product> products;
    Context context;

    public Myadapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }
    @NonNull
    @Override
    public Myadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_row,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.Myviewholder holder, int position) {
        holder.product_name.setText(products.get(position).getName());
        holder.selling_price.setText(products.get(position).getSelling_pice());
        holder.buying_price.setText(products.get(position).getBuying_price());
        holder.imageView.setImageURI(products.get(position).getImg_url());
        holder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=holder.product_name.getText().toString();
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView product_name,buying_price,selling_price,img_btn;
        RelativeLayout layout;
        ImageView imageView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.tv_regno);
            buying_price=itemView.findViewById(R.id.tv_name);
            selling_price=itemView.findViewById(R.id.tv_age);
            layout=itemView.findViewById(R.id.student_row);
            imageView=itemView.findViewById(R.id.imageView);
            img_btn=itemView.findViewById(R.id.txt_btn);

        }
    }
}


