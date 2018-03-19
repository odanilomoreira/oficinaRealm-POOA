package danilo.iff.edu.oficinarealm_pooa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.iff.pooa20172.oficinarealm.R;
import br.iff.pooa20172.oficinarealm.Model.Servico;


import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter {

    private List<Servico> servicos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public ServicoAdapter(List<Servico> servicos, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {

        this.servicos = servicos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_servico, parent, false);
        ServicoAdapter.ServicoViewHolder servicoViewHolder = new ServicoAdapter.ServicoViewHolder(view);
        return servicoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        ServicoAdapter.ServicoViewHolder holder = (ServicoAdapter.ServicoViewHolder) viewHolder;

        Servico servico  = servicos.get(position) ;

        holder.nomeServico.setText(servico.getNome());
        holder.horas.setText(Integer.toString((int) servico.getHoras()));
        holder.mecanico.setText(servico.getMecanico());



    }

    @Override
    public int getItemCount() {

        return servicos.size();
    }

    private void updateItem(int position) {

    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {

    }

    public class ServicoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeServico;
        private final TextView horas;
        private final TextView mecanico;


        public ServicoViewHolder(View itemView) {
            super(itemView);
            nomeServico = (TextView) itemView.findViewById(R.id.nomeServico);
            horas = (TextView) itemView.findViewById(R.id.horas);
            mecanico = (TextView) itemView.findViewById(R.id.mecanico);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(servicos.get(getLayoutPosition()));
                }
            });
        }
    }
}