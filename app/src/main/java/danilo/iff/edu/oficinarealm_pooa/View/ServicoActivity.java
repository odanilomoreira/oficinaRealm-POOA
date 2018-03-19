package danilo.iff.edu.oficinarealm_pooa.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.iff.pooa20172.oficinarealm.Adapter.ClickRecyclerViewListener;
import br.iff.pooa20172.oficinarealm.Adapter.ServicoAdapter;
import br.iff.pooa20172.oficinarealm.Model.Servico;
import br.iff.pooa20172.oficinarealm.R;
import io.realm.Realm;


public class ServicoActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServicoActivity.this,ServicoDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    private List<Servico> getServicos(){

        return (List)realm.where(Servico.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Servico servico = (Servico) object;
        Intent intent = new Intent(ServicoActivity.this,ServicoDetalheActivity.class);
        intent.putExtra("id",servico.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Servico);

        recyclerView.setAdapter(new ServicoAdapter(getServicos(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void finish(){
        realm.close();
    }

}