package com.androwep.androidlearingapp.ui.codedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.classifier.CodeProcessor;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.androwep.androidlearingapp.R;
import com.androwep.androidlearingapp.databinding.ActivityCodeDetailsBinding;
import com.androwep.androidlearingapp.util.local.AppConstraints;
import com.androwep.androidlearingapp.util.remote.model.CompileResponse;
import com.androwep.androidlearingapp.util.remote.retrofit.RemoteApiInterface;
import com.androwep.androidlearingapp.util.remote.retrofit.RemoteApiProvider;

public class CodeDetailsActivity extends AppCompatActivity {
    ActivityCodeDetailsBinding mBinding;
    private RemoteApiInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_code_details);
        //for api call
        mService= RemoteApiProvider.getInstance().getRemoteApi();

        String codetitel = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_TITLE);
        mBinding.toolbarTitle.setText(codetitel);
        String codeDetails = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_DETAILS);
        String codeFilter = getIntent().getStringExtra(AppConstraints.IntentConstrants.CODE_Filter);
//        mService = RemoteApiProvider.getInstance().getRemoteApi();
//        mService = RemoteApiProvider.getInstance().getRemoteApi();

// train classifier on app start
        CodeProcessor.init(this);

//        mBinding.codeview.setOptions(Options.Default.get(this).withLanguage(AppConstraints.Language.JAVA_LAN).withCode("class Simple{  \n" +
//                "    public static void main(String args[]){  \n" +
//                "     System.out.println(\"Hello Java\");  \n" +
//                "    }  \n" +
//                "}  ").withTheme(ColorTheme.MONOKAI));

          mBinding.codeview.setOptions(Options.Default.get(this).withLanguage(AppConstraints.Language.JAVA_LAN).withCode(codeDetails).withTheme(ColorTheme.MONOKAI));
          runCode(codeFilter);
//        mBinding.btnRun.setOnClickListener(v -> {
//
//        });
    }

    private void runCode(String codeFilter) {
        codeFilter = codeFilter.replace("\n", "");
        codeFilter = codeFilter.replace("\"", "\\\"");

        String string = "{\n" +
                "\t\"clientId\":\"a7e213b2d1bd70f88b593fedcc1df27\",\n" +
                "\t\"clientSecret\":\"163ef8dfba087a65851334090c9588e8a0c43b4f483e12410c950a13cbaf933\",\n" +
                "\t\"script\":\"" + codeFilter + "\",\n" +
                "\t\"stdin\":\"\",\n" +
                "\t\"language\":\"java\",\n" +
                "\t\"versionIndex\":\"0\"\n" +
                "}";

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (string));
        mService.executeCode(body).enqueue(new Callback<CompileResponse>() {
            @Override
            public void onResponse(Call<CompileResponse> call, Response<CompileResponse> response) {
                if(response.isSuccessful()){
                    CompileResponse response1 =response.body();

                    Toast.makeText(getBaseContext(), response1.toString(),Toast.LENGTH_LONG).show();

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append(response1.getOutput()+"\n");
                    stringBuilder.append("\n"+"----------"+"\n");
                    stringBuilder.append("CPU Time:"+response1.getCpuTime()+"\n");
                    stringBuilder.append("Memory:"+response1.getMemory());
                    Log.d("chk","compile success");

                    mBinding.outputtext.setText(stringBuilder);

                }
                else {
                    Log.d("chk","faild"+response.code());
                }
            }

            @Override
            public void onFailure(Call<CompileResponse> call, Throwable t) {
                Log.d("chk","faild"+t.getMessage());
            }
        });
    }
}
