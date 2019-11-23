package aron.alin.sportclubstore.Clase;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void, String> {
//    private String adresaIp = "http://192.168.100.21/";
    private String adresaIp = "http://172.20.10.3/";
    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        if(type.equals("username")){
            String URLString = adresaIp+"getusername.php";
            String userName = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("sport")){
            String URLString = adresaIp+"getsport.php";
            String sport = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("sport","UTF-8")+"="+URLEncoder.encode(sport,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getNrAdrese")){
            String URLString = adresaIp+"getidadresa.php";
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertAdress")){
            String URLString = adresaIp+"insertAdress.php";
            String idAdresa = strings[1];
            String judet = strings[2];
            String oras = strings[3];
            String strada = strings[4];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_adresa","UTF-8")+"="+URLEncoder.encode(idAdresa,"UTF-8")+"&"+
                        URLEncoder.encode("judet","UTF-8")+"="+URLEncoder.encode(judet,"UTF-8")+"&"+
                        URLEncoder.encode("oras","UTF-8")+"="+URLEncoder.encode(oras,"UTF-8")+"&"+
                        URLEncoder.encode("strada","UTF-8")+"="+URLEncoder.encode(strada,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("updateAdresa")){
            String URLString = adresaIp+"updateAdresa.php";
            String idAdresa = strings[1];
            String judet = strings[2];
            String oras = strings[3];
            String strada = strings[4];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_adresa","UTF-8")+"="+URLEncoder.encode(idAdresa,"UTF-8")+"&"+
                        URLEncoder.encode("judet","UTF-8")+"="+URLEncoder.encode(judet,"UTF-8")+"&"+
                        URLEncoder.encode("oras","UTF-8")+"="+URLEncoder.encode(oras,"UTF-8")+"&"+
                        URLEncoder.encode("strada","UTF-8")+"="+URLEncoder.encode(strada,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertUser")){
            String URLString = adresaIp+"insertUser.php";
            String username = strings[1];
            String parola = strings[2];
            String nume = strings[3];
            String prenume = strings[4];
            String telefon = strings[5];
            String dataNastere = strings[6];
            String idSport = strings[7];
            String sex = strings[8];
            String idAdresa = strings[9];
            String poza = strings [10];
            String bio = strings[11];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("parola","UTF-8")+"="+URLEncoder.encode(parola,"UTF-8")+"&"+
                        URLEncoder.encode("nume","UTF-8")+"="+URLEncoder.encode(nume,"UTF-8")+"&"+
                        URLEncoder.encode("prenume","UTF-8")+"="+URLEncoder.encode(prenume,"UTF-8")+"&"+
                        URLEncoder.encode("telefon","UTF-8")+"="+URLEncoder.encode(telefon,"UTF-8")+"&"+
                        URLEncoder.encode("data_nastere","UTF-8")+"="+URLEncoder.encode(dataNastere,"UTF-8")+"&"+
                        URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8")+"&"+
                        URLEncoder.encode("sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")+"&"+
                        URLEncoder.encode("id_adresa","UTF-8")+"="+URLEncoder.encode(idAdresa,"UTF-8")+"&"+
                        URLEncoder.encode("poza","UTF-8")+"="+URLEncoder.encode(poza,"UTF-8")+"&"+
                        URLEncoder.encode("bio","UTF-8")+"="+URLEncoder.encode(bio,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("updateUser")){
            String URLString = adresaIp+"updateUser.php";
            String idClient = strings[1];
            String username = strings[2];
            String parola = strings[3];
            String nume = strings[4];
            String prenume = strings[5];
            String telefon = strings[6];
            String dataNastere = strings[7];
            String idSport = strings[8];
            String sex = strings[9];
            String poza = strings [10];
            String bio = strings[11];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData =
                        URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("parola","UTF-8")+"="+URLEncoder.encode(parola,"UTF-8")+"&"+
                        URLEncoder.encode("nume","UTF-8")+"="+URLEncoder.encode(nume,"UTF-8")+"&"+
                        URLEncoder.encode("prenume","UTF-8")+"="+URLEncoder.encode(prenume,"UTF-8")+"&"+
                        URLEncoder.encode("telefon","UTF-8")+"="+URLEncoder.encode(telefon,"UTF-8")+"&"+
                        URLEncoder.encode("data_nastere","UTF-8")+"="+URLEncoder.encode(dataNastere,"UTF-8")+"&"+
                        URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8")+"&"+
                        URLEncoder.encode("sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")+"&"+
                        URLEncoder.encode("poza","UTF-8")+"="+URLEncoder.encode(poza,"UTF-8")+"&"+
                        URLEncoder.encode("bio","UTF-8")+"="+URLEncoder.encode(bio,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("login")){
            String URLString = adresaIp+"login.php";
            String username = strings[1];
            String password = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("forgotPass")){
            String URLString = adresaIp+"forgotPass.php";
            String username = strings[1];
            String telefon = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("telefon","UTF-8")+"="+URLEncoder.encode(telefon,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("mainpagecomunity")){
            String URLString = adresaIp+"mainpagecomunity.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCategorie")){
            String URLString = adresaIp+"getCategorie.php";
            String idCategorie = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_categorie","UTF-8")+"="+URLEncoder.encode(idCategorie,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getImage")){
            String URLString = adresaIp+"getImages.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getProduseRecomandate")){
            String URLString = adresaIp+"getProduseRecomandate.php";
            String idSport = strings[1];
            String sex = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8")+"&"+
                        URLEncoder.encode("sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getUltimeleProduse")){
            String URLString = adresaIp+"getUltimeleProduse.php";
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCategorii")){
            String URLString = adresaIp+"getCategorii.php";
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("search")){
            String URLString = adresaIp+"search.php";
            String keyword = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("keyword","UTF-8")+"="+URLEncoder.encode(keyword,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getAdresaClient")){
            String URLString = adresaIp+"getAdresaClient.php";
            String idAdresa = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_adresa","UTF-8")+"="+URLEncoder.encode(idAdresa,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getAllImages")){
            String URLString = adresaIp+"getAllImages.php";
            String idSport = strings[1];
            String judet = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8")+"&"+
                        URLEncoder.encode("judet","UTF-8")+"="+URLEncoder.encode(judet,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getPostari")){
            String URLString = adresaIp+"getPostari.php";
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getInfoProdus")){
            String URLString = adresaIp+"getInfoProdus.php";
            String idProdus = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("updateStoc")){
            String URLString = adresaIp+"updateStoc.php";
            String idProdus = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie="";
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getLocatii")){
            String URLString = adresaIp+"getLocatii.php";
            String idSport = strings[1];
            String judet = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8")+"&"+
                        URLEncoder.encode("judet","UTF-8")+"="+URLEncoder.encode(judet,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCompetitii")){
            String URLString = adresaIp+"getCompetitii.php";
            String idSport = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_sport","UTF-8")+"="+URLEncoder.encode(idSport,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getComentarii")){
            String URLString = adresaIp+"getComentarii.php";
            String idPostare = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_postare","UTF-8")+"="+URLEncoder.encode(idPostare,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertFavorite")){
            String URLString = adresaIp+"insertFavorite.php";
            String idClient = strings[1];
            String codFavorite = strings[2];
            String idFavorite = strings[3];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("cod_favorit","UTF-8")+"="+URLEncoder.encode(codFavorite,"UTF-8")+"&"+
                        URLEncoder.encode("id_favorit","UTF-8")+"="+URLEncoder.encode(idFavorite,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertComment")){
            String URLString = adresaIp+"insertComment.php";
            String idClient = strings[3];
            String continut = strings[2];
            String idPostare = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_postare","UTF-8")+"="+URLEncoder.encode(idPostare,"UTF-8")+"&"+
                        URLEncoder.encode("continut","UTF-8")+"="+URLEncoder.encode(continut,"UTF-8")+"&"+
                        URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertComanda")){
            String URLString = adresaIp+"insertComanda.php";
            String idClient = strings[2];
            String nrComanda = strings[1];
            String valoare = strings[3];
            String modalitatePlata = strings[4];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("nr_comanda","UTF-8")+"="+URLEncoder.encode(nrComanda,"UTF-8")+"&"+
                        URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("valoare","UTF-8")+"="+URLEncoder.encode(valoare,"UTF-8")+"&"+
                        URLEncoder.encode("modalitate_plata","UTF-8")+"="+URLEncoder.encode(modalitatePlata,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertRandComenzi")){
            String URLString = adresaIp+"insertRandComenzi.php";
            String nrComanda = strings[1];
            String idProdus = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("nr_comanda","UTF-8")+"="+URLEncoder.encode(nrComanda,"UTF-8")+"&"+
                        URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getProduseCategorii")){
            String URLString = adresaIp+"getProduseCategorii.php";
            String idCategorie = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_categorie","UTF-8")+"="+URLEncoder.encode(idCategorie,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("deleteFavorite")){
            String URLString = adresaIp+"deleteFavorite.php";
            String idClient = strings[1];
            String codFavorite = strings[2];
            String idFavorite = strings[3];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("cod_favorit","UTF-8")+"="+URLEncoder.encode(codFavorite,"UTF-8")+"&"+
                        URLEncoder.encode("id_favorit","UTF-8")+"="+URLEncoder.encode(idFavorite,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie="";
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("deleteCos")){
            String URLString = adresaIp+"deleteCos.php";
            String idClient = strings[1];
            String idProdus = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie="";
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("deleteWishlist")){
            String URLString = adresaIp+"deleteWishlist.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie="";
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("deleteAllCos")){
            String URLString = adresaIp+"deleteAllCos.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie="";
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getFavoriteAll")){
            String URLString = adresaIp+"getFavoriteAll.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getMyPosts")){
            String URLString = adresaIp+"getMyPosts.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getMyComments")){
            String URLString = adresaIp+"getMyComments.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getComenzi")){
            String URLString = adresaIp+"getComenzi.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getProduseComenzi")){
            String URLString = adresaIp+"getProduseComenzi.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getRandComenzi")){
            String URLString = adresaIp+"getRandComenzi.php";
            String nrComanda = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("nr_comanda","UTF-8")+"="+URLEncoder.encode(nrComanda,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getMyProducts")){
            String URLString = adresaIp+"getMyProducts.php";
            String nrComanda = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("nr_comanda","UTF-8")+"="+URLEncoder.encode(nrComanda,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getLocatie")){
            String URLString = adresaIp+"getLocatie.php";
            String idLocatie = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_locatie","UTF-8")+"="+URLEncoder.encode(idLocatie,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCompetitie")){
            String URLString = adresaIp+"getCompetitie.php";
            String idCompetitie = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_competitie","UTF-8")+"="+URLEncoder.encode(idCompetitie,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getFavorite")){
            String URLString = adresaIp+"getFavorite.php";
            String idClient = strings[1];
            String codFavorit = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("cod_favorit","UTF-8")+"="+URLEncoder.encode(codFavorit,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getWishlist")){
            String URLString = adresaIp+"getWishlist.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCos")){
            String URLString = adresaIp+"getCos.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getNrComenzi")){
            String URLString = adresaIp+"getNrComenzi.php";
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getUrmaritori")){
            String URLString = adresaIp+"getUrmaritori.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getFolloweri")){
            String URLString = adresaIp+"getFolloweri.php";
            String idClient = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_favorit","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getSearch")){
            String URLString = adresaIp+"getSearch.php";
            String keyword = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("keyword","UTF-8")+"="+URLEncoder.encode(keyword,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getPostare")){
            String URLString = adresaIp+"getPostare.php";
            String idPostare = strings[1];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_postare","UTF-8")+"="+URLEncoder.encode(idPostare,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertProdus")){
            String URLString = adresaIp+"insertProdus.php";
            String idClient = strings[1];
            String idProdus = strings[2];
            String marime = strings[3];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8")+"&"+
                        URLEncoder.encode("marime","UTF-8")+"="+URLEncoder.encode(marime,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertProdus2")){
            String URLString = adresaIp+"insertProdus2.php";
            String idClient = strings[1];
            String idProdus = strings[2];
            String marime = strings[3];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idClient,"UTF-8")+"&"+
                        URLEncoder.encode("id_produs","UTF-8")+"="+URLEncoder.encode(idProdus,"UTF-8")+"&"+
                        URLEncoder.encode("marime","UTF-8")+"="+URLEncoder.encode(marime,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertPost")){
            String URLString = adresaIp+"insertPost.php";
            String continut = strings[1];
            String poza = strings[2];
            String idCl = strings[3];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("continut","UTF-8")+"="+URLEncoder.encode(continut,"UTF-8")+"&"+
                        URLEncoder.encode("poza","UTF-8")+"="+URLEncoder.encode(poza,"UTF-8")+"&"+
                        URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idCl,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertPost2")){
            String URLString = adresaIp+"insertPost2.php";
            String continut = strings[1];
            String idCl = strings[2];
            try {
                URL url = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("continut","UTF-8")+"="+URLEncoder.encode(continut,"UTF-8")+"&"+
                        URLEncoder.encode("id_client","UTF-8")+"="+URLEncoder.encode(idCl,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder result = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine())!=null)
                    result.append(linie);
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
