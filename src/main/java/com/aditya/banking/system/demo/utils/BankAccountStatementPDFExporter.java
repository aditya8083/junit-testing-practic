package com.aditya.banking.system.demo.utils;

import com.aditya.banking.system.demo.entity.dao.BankAccountTransaction;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BankAccountStatementPDFExporter {

    private List<BankAccountTransaction> bankAccountTransactionList;

    public BankAccountStatementPDFExporter(List<BankAccountTransaction> bankAccountTransactionList) {
        this.bankAccountTransactionList = bankAccountTransactionList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Transaction-Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Transaction-Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Withdrawal", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Deposit", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Closing-Balance", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (BankAccountTransaction bankAccountTransaction : bankAccountTransactionList) {
            table.addCell(String.valueOf(bankAccountTransaction.getId()));
            table.addCell(String.valueOf(bankAccountTransaction.getTransactionDate()));
            table.addCell(String.valueOf(bankAccountTransaction.getWithdrawalAmount()));
            table.addCell(String.valueOf(bankAccountTransaction.getDepositAmount()));
            table.addCell(String.valueOf(bankAccountTransaction.getClosingBalance()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Transactions for Account No. " + bankAccountTransactionList.get(0).getBankAccountNumber() , font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
