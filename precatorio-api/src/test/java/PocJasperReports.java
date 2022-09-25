import br.com.precatorio.cliente.ClienteRepository;
import br.com.precatorio.system.util.NumeroPorExtenso;
import org.springframework.beans.factory.annotation.Autowired;

public class PocJasperReports {
    @Autowired
    ClienteRepository repository;

    public static void main(String[] args) {
//        PocJasperReports obj = new PocJasperReports();
//        obj.exportReport("pdf");
        Double valor = 155523533.;
        System.out.println( new NumeroPorExtenso(valor).toString() );
    }

    public String exportReport(String reportFormat) {
//        String path = "c:\\test";
//        List<Cliente> Clientes = repository.findAll();
//        //load file and compile it
//        File file = ResourceUtils.getFile("classpath:cliente.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Clientes);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Java Techie");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Clientes.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Clientes.pdf");
//        }

        return "report generated in path : " ;
    }

}
