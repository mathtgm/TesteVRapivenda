package br.com.matheus.testevr.apivenda.repository;

import br.com.matheus.testevr.apivenda.model.ProdutoAPIErro;
import br.com.matheus.testevr.apivenda.model.ProdutoAPIFicticiaBaixaEstoque;
import br.com.matheus.testevr.apivenda.model.ProdutoAPIRespostaHttp;
import br.com.matheus.testevr.apivenda.model.VendaProduto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoAPIRepository {

    public Map<String, ProdutoAPIErro> baixaEstoqueEmLote(List<VendaProduto> listaVendaProduto) throws IOException, InterruptedException {


        List<ProdutoAPIFicticiaBaixaEstoque> listaProdutoAPI = new ArrayList<>();
        Gson gson = new Gson();

        for (VendaProduto produto : listaVendaProduto) {

            listaProdutoAPI.add(new ProdutoAPIFicticiaBaixaEstoque(produto.getIdProduto(), produto.getQuantidadeVenda()));

        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/produtos/baixa"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(listaProdutoAPI)))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, ProdutoAPIErro> listaProdutosError = new HashMap<>();

        if (httpResponse.statusCode() == 207) {

            listaProdutosError = gson.fromJson(httpResponse.body(), ProdutoAPIRespostaHttp.class).errors();

        } else if (httpResponse.statusCode() == 400) {

            listaProdutosError = gson.fromJson(httpResponse.body(), new TypeToken<Map<String, ProdutoAPIErro>>() {
            }.getType());
        }

        return listaProdutosError;

    }


}
