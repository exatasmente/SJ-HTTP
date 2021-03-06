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

public class SJHTTP 
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
            throw new IllegalArgumentException("Argumentos inv�lidos");

        }
        if(p<6000)
            throw new IllegalArgumentException("Porta inv�lida");
        InetSocketAddress Socket = new InetSocketAddress(p);
        HttpServer servidor = HttpServer.create(Socket, 10);
        servidor.createContext("/", new InfoHandler());
        servidor.setExecutor(null);
        servidor.start();
        System.out.println("Servidor Online");

    }

    static class InfoHandler implements HttpHandler 
    {
        public void handle(HttpExchange httpExchange) throws IOException 
        {
            String phaser ="";
            String url ="";
            String dir = httpExchange.getRequestURI().toString();
            if(dir.equals("/"))
            {
                dir ="/index.html";
            }

            System.out.println("Diret�rio "+dir);

            phaser =  phaserCheck(dir);  
            if(phaser == "403")
            {
                errorPhaser(httpExchange,phaser);

            }
            if(phaser == "404")
            {
                errorPhaser(httpExchange,phaser);
            }
            else
            {

                url =fileType(dir);
                if(url != null)
                {
                    System.out.println(url);
                    GetHandle(httpExchange, url, dir);
                }
                else{
                    File file = new File(phaser);
                    if(file.exists()== false)
                    {
                        errorPhaser(httpExchange,"404");
                    }                
                    else
                    {
                        System.out.println("Arquivo "+phaser);
                        BufferedReader buffRead = new BufferedReader(new FileReader(phaser));

                        String response ="";
                        String linha = "";
                        System.out.println(buffRead);

                        while (true)
                        {
                            if (linha != null)
                            {
                                response +=linha;
                                response +="\n";
                            } else{
                                break;
                            }
                            linha = buffRead.readLine();
                        }

                        buffRead.close();

                        String resposta = response;

                        SJHTTP.writeResponse(httpExchange, resposta);
                    }
                }
            }

        }

        private static String phaserCheck(String arquivo)throws IOException 
        {
            BufferedReader buffRead = new BufferedReader(new FileReader("filesdir.dat"));

            String resposta ="404";
            String linha = "";
            String linha0 = "";
            String linha1 = "";
            char[] arq = arquivo.toCharArray();
            arq[0]=' ';

            arquivo = String.valueOf(arq);    
            arquivo = arquivo.trim();

            while (true)
            {
                if (linha != null)
                {
                    linha0 = linha.replace("wwwroot/","");

                    linha1 = linha0.replace("*","");

                    if(linha1.equals(arquivo))
                    {
                        if(linha.indexOf("*")> -1)
                        {
                            resposta = "403";
                            break;
                        }
                        resposta=linha.replace("*","");
                        break;
                    }
                } else
                    break;
                linha = buffRead.readLine();
            }

            buffRead.close();

            return resposta;
        }

        private static String fileType(String arquivo)throws IOException 
        {
            BufferedReader buffRead = new BufferedReader(new FileReader("filetypelist.dat"));

            String resposta =null;
            String linha = "";
            String linha0 = "";
            char[] arq = arquivo.toCharArray();
            arq[0]=' ';
            String ab = String.valueOf(arq);    
            String ar = ab.trim();
            String ac = ar.replace("."," ");
            String[] a = ac.split(" ");
            System.out.println(a[1]);
            while (true)
            {
                if (linha != null)
                {
                    linha0 = linha.replace("*","");
                    if(a[1].equals(linha))
                    {
                        System.out.println(linha);
                        if(linha.indexOf("*")> -1)
                        {
                            return resposta;
                        }
                        resposta = linha;
                        return resposta;
                    }
                } else
                    break;
                linha = buffRead.readLine();
            }

            buffRead.close();

            return resposta;
        }

        private static void errorPhaser(HttpExchange httpExchange, String errorparam)throws IOException 
        {
            errorparam +=".html";
            System.out.println("---------------------"+errorparam);
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
            SJHTTP.writeResponse(httpExchange, respostaErro);

        }

        public void GetHandle(HttpExchange t, String type,String dir) throws IOException 
        {

            // add the required response header for a PDF file
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/"+type);

            File file = new File ("wwwroot/"+dir);
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
        System.out.println("CLIENTE :"+httpExchange.getRemoteAddress());
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
