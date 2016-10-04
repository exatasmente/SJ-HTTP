// Bibliotecas Usadas pra manipular arquivos
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
//Biblioteca usada para tratamento de Exessões E/S
import java.io.IOException;

import java.io.OutputStream;
import java.net.InetSocketAddress;

//Bibliotecas usadas par Criação do servidor java HTTP
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

public class ServidorHTTPJava 
{

    public static void main(String[] args) throws Exception 
    {
        int p=0;
        menuload();
        switch(args[0])
        {
            case "-p":
            p =Integer.parseInt(args[1]);
            break;
            case "-h":
            helper(); 
            break;

            default:
            throw new IllegalArgumentException("Argumentos inválidos");

        }
        if(p<6000)
            throw new IllegalArgumentException("Porta inválida");
        InetSocketAddress Socket = new InetSocketAddress(p);
        HttpServer servidor = HttpServer.create(Socket, 0);
        servidor.createContext("/", new InfoHandler());
        servidor.createContext("/sjhttp.zip", new GetHandler());
        servidor.setExecutor(null);
        servidor.start();
        System.out.println("Servidor Online");

    }

    static class InfoHandler implements HttpHandler 
    {
        public void handle(HttpExchange httpExchange) throws IOException 
        {
            String phaser ="";
            String dir = httpExchange.getRequestURI().toString();
            System.out.println(dir);
            if(dir.equals("/"))
            {
                dir ="/index.html";
            }
            phaser =  phaserCheck(dir);  
            if(phaser == "403")
            {
                String erro =errorPhaser(phaser);
                ServidorHTTPJava.writeResponse(httpExchange,erro);

            }
            if(phaser == "404")
            {
                String erro = errorPhaser(phaser);
                ServidorHTTPJava.writeResponse(httpExchange,erro);

            }
            
                System.out.println(phaser);
                BufferedReader buffRead = new BufferedReader(new FileReader(phaser));
                String response ="";
                String linha = "";
                while (true)
                {
                    if (linha != null)
                    {
                        response +=linha;
                        response +="\n";
                    } else
                        break;
                    linha = buffRead.readLine();
                }

                buffRead.close();

                String resposta = response;

                ServidorHTTPJava.writeResponse(httpExchange, resposta);
            
        }

        private static String phaserCheck(String arquivo)throws IOException 
        {
            BufferedReader buffRead = new BufferedReader(new FileReader("filesdir.txt"));

            String resposta ="404";
            String linha = "";
            String linha0 = "";
            char[] arq = arquivo.toCharArray();
            arq[0]=' ';

            arquivo = String.valueOf(arq);    
            arquivo = arquivo.trim();

            while (true)
            {
                if (linha != null)
                {
                    linha0 = linha.replace("*","");
                    if(linha0.equals(arquivo))
                    {
                        if(linha.indexOf("*")> -1)
                        {
                            resposta = "403";
                            break;
                        }
                        resposta=linha;

                        break;
                    }
                } else
                    break;
                linha = buffRead.readLine();
            }

            buffRead.close();

            return resposta;
        }

        private static String errorPhaser(String errorparam)throws IOException 
        {
            errorparam +=".html";
            BufferedReader buffRead = new BufferedReader(new FileReader("erros/errofilesdir.dat"));

            String resposta =null;
            String linha = "";

            String ERRO = "";

            while (true)
            {
                if (linha != null)
                {

                    if(linha.equals(errorparam))
                    {
                        ERRO = linha;
                        break;
                    }
                } else
                    break;
                linha = buffRead.readLine();
            }

            buffRead.close();
            String html ="erros/";
            html +=ERRO;
            BufferedReader buffReadErro = new BufferedReader(new FileReader(html));

            String linha1 = "";
            String respostaErro = "";

            while (true)
            {
                if (linha1 != null)
                {
                    respostaErro +=linha1;
                    respostaErro +="\n";
                } else
                    break;
                linha1 = buffReadErro.readLine();
            }

            buffReadErro.close();

            return respostaErro;
        }
    }

    static class GetHandler implements HttpHandler
    {
        public void handle(HttpExchange t) throws IOException 
        {

            // add the required response header for a PDF file
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/zip");

            // a PDF (you provide your own!)
            File file = new File ("TR 04.zip");
            byte [] bytearray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);

            // ok, we are ready to send the response.
            t.sendResponseHeaders(200, file.length());
            OutputStream os = t.getResponseBody();
            os.write(bytearray,0,bytearray.length);
            os.close();
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException
    {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void menuload()throws IOException
    {
        String files ="help/start.help";
        BufferedReader buffRead = new BufferedReader(new FileReader(files));

        String linha = "";
        while (true)
        {
            if (linha != null)
            {
                System.out.println(linha);

            } else
                break;
            linha = buffRead.readLine();
        }

        buffRead.close();

    }
    public static void helper()throws IOException
    {
        String files ="help/help.help";
        BufferedReader buffRead = new BufferedReader(new FileReader(files));

        String linha = "";
        while (true)
        {   
            if (linha != null)
            {
                System.out.println(linha);

            } else
                break;
            linha = buffRead.readLine();
        }

        buffRead.close();

    }
    
}
