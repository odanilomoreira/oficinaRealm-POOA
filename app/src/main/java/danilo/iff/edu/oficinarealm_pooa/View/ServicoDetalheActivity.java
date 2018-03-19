package danilo.iff.edu.oficinarealm_pooa.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.iff.pooa20172.oficinarealm.Model.Servico;
import br.iff.pooa20172.oficinarealm.R;
import io.realm.Realm;


public class ServicoDetalheActivity extends AppCompatActivity {

    EditText nome, horas, mecanico;
    Button btsalvar,btalterar, btdeletar;

    int id;
    Servico servico;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_servico);
        horas = (EditText) findViewById(R.id.ed_horas);
        mecanico = (EditText) findViewById(R.id.ed_mecanico);

        btsalvar = (Button) findViewById(R.id.bt_salvar_servico);
        btalterar = (Button) findViewById(R.id.bt_alterar_servico);
        btdeletar = (Button) findViewById(R.id.bt_deletar_servico);

        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            servico = realm.where(Servico.class).equalTo("id",id).findFirst();

            nome.setText(servico.getNome());
            horas.setText(Integer.toString((int) servico.getHoras()));
            mecanico.setText(servico.getMecanico());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }



        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar(){
        realm.beginTransaction();
        servico.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Serviço Deletado Com Sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Servico.class).max("id") !=null)
            proximoID = realm.where(Servico.class).max("id").intValue()+1;

        realm.beginTransaction();
        Servico servico = new Servico();
        servico.setId(proximoID);
        servico.setNome(nome.getText().toString());
        servico.setHoras(Integer.parseInt(horas.getText().toString()));
        servico.setMecanico(mecanico.getText().toString());

        realm.copyToRealm(servico);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Serviço Cadastrado Com Sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void alterar() {

        realm.beginTransaction();

        servico.setNome(nome.getText().toString());
        servico.setHoras(Integer.parseInt(horas.getText().toString()));
        servico.setMecanico(mecanico.getText().toString());

        realm.copyToRealm(servico);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Serviço Alterado Com Sucesso!", Toast.LENGTH_LONG).show();
        this.finish();

    }

}

