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
import br.iff.pooa20172.oficinarealm.Adapter.PecaAdapter;
import br.iff.pooa20172.oficinarealm.Model.Peca;
import br.iff.pooa20172.oficinarealm.R;
import io.realm.Realm;


public class PecaActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PecaActivity.this,PecaDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    private List<Peca> getPecas(){

        return (List)realm.where(Peca.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Peca peca = (Peca) object;
        Intent intent = new Intent(PecaActivity.this,PecaDetalheActivity.class);
        intent.putExtra("id",peca.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Peca);

        recyclerView.setAdapter(new PecaAdapter(getPecas(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void finish(){
        realm.close();
    }

}