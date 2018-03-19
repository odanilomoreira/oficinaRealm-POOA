package danilo.iff.edu.oficinarealm_pooa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.iff.pooa20172.oficinarealm.R;
import br.iff.pooa20172.oficinarealm.Model.Proprietario;

import java.util.List;


public class ProprietarioAdapter extends RecyclerView.Adapter {

    private List<Proprietario> prorprietarios;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public ProprietarioAdapter(List<Proprietario> prorprietarios, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {

        this.prorprietarios = prorprietarios;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_proprietario, parent, false);
        ProprietarioAdapter.ProprietarioViewHolder proprietarioViewHolder = new ProprietarioAdapter.ProprietarioViewHolder(view);
        return proprietarioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        ProprietarioAdapter.ProprietarioViewHolder holder = (ProprietarioAdapter.ProprietarioViewHolder) viewHolder;

        Proprietario proprietario = prorprietarios.get(position) ;

        holder.nomeProprietario.setText(proprietario.getNome());
        holder.endereco.setText(proprietario.getEndereco());
        holder.telefone.setText(proprietario.getTelefone());



    }

    @Override
    public int getItemCount() {

        return prorprietarios.size();
    }

    private void updateItem(int position) {

    }

    private void removerItem(int position) {

    }

    public class ProprietarioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeProprietario;
        private final TextView endereco;
        private final TextView telefone;


        public ProprietarioViewHolder(View itemView) {
            super(itemView);
            nomeProprietario = (TextView) itemView.findViewById(R.id.nomeProprietario);
            endereco = (TextView) itemView.findViewById(R.id.endereco);
            telefone = (TextView) itemView.findViewById(R.id.telefone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(prorprietarios.get(getLayoutPosition()));
                }
            });
        }
    }
}
