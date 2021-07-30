package com.hasid.pdf;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfEncodings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnCreate;
    EditText editText;
    ProgressDialog pDialog;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog=new ProgressDialog(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        btnCreate = (Button)findViewById(R.id.create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                pDialog.setMessage("Please wait for creating pdf");
                pDialog.show();
                try {
                    createPdf("Hasid");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    FileOutputStream outputStream;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(String sometext) throws FileNotFoundException, DocumentException {
        Document doc = new Document();

        try {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "/mypdf");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
            }
            Date date = new Date() ;
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

             file = new File(pdfFolder + timeStamp + ".pdf");
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mypdf";
//
//            File dir = new File(path);
//            if(!dir.exists())
//                dir.mkdirs();
//
//            File file = new File(dir, "test-2.pdf");
            OutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);
            PdfDocument pdfDocument=new PdfDocument();
            float[] width={60,40};

            doc.open();
            PdfPTable pdfPTable = new PdfPTable(1);
            PdfPTable pdfPTable1 = new PdfPTable(2);
            PdfPTable pdfPTable2 = new PdfPTable(2);
            PdfPTable pdfPTable3 = new PdfPTable(5);
            PdfPTable pdfPTable4 = new PdfPTable(5);
            PdfPTable pdfPTable5 = new PdfPTable(3);
            PdfPTable pdfPTable6 = new PdfPTable(3);
            PdfPTable pdfPTable7 = new PdfPTable(3);
            pdfPTable5.setWidths(new float[] { 3, 1,1 });
            pdfPTable6.setWidths(new float[] { 3, 1,1 });
            pdfPTable7.setWidths(new float[] { 3, 1,1 });

            pdfPTable.setHorizontalAlignment(0);
            pdfPTable.setTotalWidth(500f);
            pdfPTable.setLockedWidth(true);
            pdfPTable1.setHorizontalAlignment(0);
            pdfPTable1.setTotalWidth(500f);
            pdfPTable1.setLockedWidth(true);
            pdfPTable2.setHorizontalAlignment(0);
            pdfPTable2.setTotalWidth(500f);
            pdfPTable2.setLockedWidth(true);
            pdfPTable3.setHorizontalAlignment(0);
            pdfPTable3.setTotalWidth(500f);
            pdfPTable3.setLockedWidth(true);
            pdfPTable4.setHorizontalAlignment(0);
            pdfPTable4.setTotalWidth(500f);
            pdfPTable4.setLockedWidth(true);
            pdfPTable5.setHorizontalAlignment(0);
            pdfPTable5.setTotalWidth(500f);
            pdfPTable5.setLockedWidth(true);
            pdfPTable6.setHorizontalAlignment(0);
            pdfPTable6.setTotalWidth(500f);
            pdfPTable6.setLockedWidth(true);
            pdfPTable7.setHorizontalAlignment(0);
            pdfPTable7.setTotalWidth(500f);
            pdfPTable7.setLockedWidth(true);

            // load image
            Image image;
            try {
                // get input stream
                InputStream ims = getAssets().open("logo.png");
                Bitmap bmp = BitmapFactory.decodeStream(ims);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                 image = Image.getInstance(stream.toByteArray());
            }
            catch(IOException ex)
            {
                return;
            }
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
            String date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

            //Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("  M/s  Hasid Mansoori"));
            pdfPCell1.setBackgroundColor(BaseColor.WHITE);
            pdfPCell1.setPadding(5);
            pdfPCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("   Invoice No.: 12345"));
            pdfPCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell2.setPadding(5);
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Room no 710,7th Floor,Bldg no 7b,Mhada Colony,Govandi(W),Mumbai-400043  "));
            pdfPCell3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell3.setPadding(5);
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("  Date: "+date1));
            pdfPCell4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell4.setPadding(5);
            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("  Sr No ",boldFont));
            pdfPCell5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell5.setPadding(5);
            PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("  Descript",boldFont));
            pdfPCell6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell6.setPadding(5);
            PdfPCell pdfPCell7 = new PdfPCell(new Paragraph("  Hsc code ",boldFont));
            pdfPCell7.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell7.setPadding(5);
            PdfPCell pdfPCell8 = new PdfPCell(new Paragraph("  Qty ",boldFont));
            pdfPCell8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell8.setPadding(5);
            PdfPCell pdfPCell9 = new PdfPCell(new Paragraph("  Rate ",boldFont));
            pdfPCell9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell9.setPadding(5);
            PdfPCell pdfPCell10 = new PdfPCell(new Paragraph("  1 "));
            pdfPCell10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell10.setPadding(5);
            PdfPCell pdfPCell11 = new PdfPCell(new Paragraph("  Mens Wear "));
//            pdfPCell11.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell11.setPadding(5);
            PdfPCell pdfPCell12 = new PdfPCell(new Paragraph("   "));
            PdfPCell pdfPCell13 = new PdfPCell(new Paragraph("  1 "));
            pdfPCell13.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell12.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell12.setPadding(5);
            pdfPCell13.setPadding(5);
            PdfPCell pdfPCell14 = new PdfPCell(new Paragraph("  5000"));
            pdfPCell14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell14.setPadding(5);
            PdfPCell pdfPCell15 = new PdfPCell(new Paragraph("Rupees in words: \n Five Thousand Nine Hundred",boldFont));
            pdfPCell15.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell15.setPadding(5);
            PdfPCell pdfPCell16 = new PdfPCell(new Paragraph("Total ",boldFont));
            pdfPCell16.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell16.setPadding(5);
            PdfPCell pdfPCell17 = new PdfPCell(new Paragraph("5900",boldFont));
            PdfPCell pdfPCell22 = new PdfPCell(new Paragraph("  "));
            pdfPCell17.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell17.setPadding(5);
            PdfPCell pdfPCell18 = new PdfPCell(new Paragraph("CGST 9% ",boldFont));
            pdfPCell18.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell18.setPadding(5);
            PdfPCell pdfPCell19 = new PdfPCell(new Paragraph("450",boldFont));
            pdfPCell19.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell19.setPadding(5);
            PdfPCell pdfPCell20 = new PdfPCell(new Paragraph("SGST 9% ",boldFont));
            pdfPCell20.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell20.setPadding(5);
            PdfPCell pdfPCell21 = new PdfPCell(new Paragraph("450",boldFont));
            pdfPCell21.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfPCell21.setPadding(5);
            pdfPCell10.setFixedHeight(250f);
            pdfPCell11.setFixedHeight(250f);
            pdfPCell12.setFixedHeight(250f);
            pdfPCell13.setFixedHeight(250f);
            pdfPCell14.setFixedHeight(250f);
            PdfPCell pdfpcell=new PdfPCell();
            pdfpcell.addElement(image);

            //Add cells to table
            pdfPTable.addCell(pdfpcell);
            pdfPTable1.addCell(pdfPCell1);
            pdfPTable1.addCell(pdfPCell2);
            pdfPTable2.addCell(pdfPCell3);
            pdfPTable2.addCell(pdfPCell4);
            pdfPTable3.addCell(pdfPCell5);
            pdfPTable3.addCell(pdfPCell6);
            pdfPTable3.addCell(pdfPCell7);
            pdfPTable3.addCell(pdfPCell8);
            pdfPTable3.addCell(pdfPCell9);
            pdfPTable4.addCell(pdfPCell10);
            pdfPTable4.addCell(pdfPCell11);
            pdfPTable4.addCell(pdfPCell12);
            pdfPTable4.addCell(pdfPCell13);
            pdfPTable4.addCell(pdfPCell14);
            pdfPTable5.addCell(pdfPCell15);
            pdfPTable5.addCell(pdfPCell16);
            pdfPTable5.addCell(pdfPCell17);
            pdfPTable6.addCell(pdfPCell22);
            pdfPTable6.addCell(pdfPCell18);
            pdfPTable6.addCell(pdfPCell19);
            pdfPTable7.addCell(pdfPCell22);
            pdfPTable7.addCell(pdfPCell20);
            pdfPTable7.addCell(pdfPCell21);

            //Add content to the document using Table objects.
            doc.add(pdfPTable);
            doc.add(pdfPTable1);
            doc.add(pdfPTable2);
            doc.add(pdfPTable3);
            doc.add(pdfPTable4);
            doc.add(pdfPTable6);
            doc.add(pdfPTable7);
            doc.add(pdfPTable5);

            //Close document and outputStream.
            doc.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                pDialog.dismiss();
                openPDF();
            }
        }, 10000); // 10000 milliseconds delay

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void openPDF(){

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(FileProvider.getUriForFile(MainActivity.this, getApplicationContext().getPackageName() + ".provider", file),"application/pdf");
        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            // Instruct the user to install a PDF reader here, or something
        }
    }
}