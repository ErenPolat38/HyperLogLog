import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HyperLogLog {
    private final int p; // Hassasiyet derecesibility
    private final int m; // Kova sayısı (2^p)
    private final double alpha;
    private final byte[] registers;

    public HyperLogLog(int p) {
        if (p < 4 || p > 16) throw new IllegalArgumentException("p 4 ile 16 arasında olmalıdır.");
        this.p = p;
        this.m = 1 << p;
        this.registers = new byte[m];
        this.alpha = calculateAlpha(m);
    }

    private double calculateAlpha(int m) {
        if (m == 16) return 0.673;
        if (m == 32) return 0.697;
        if (m == 64) return 0.709;
        return 0.7213 / (1 + 1.079 / m);
    }

    // Yüksek kaliteli 64-bit hash (SHA-256'nın ilk 8 byte'ı)
    private long hash(String item) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(item.getBytes(StandardCharsets.UTF_8));
            long result = 0;
            for (int i = 0; i < 8; i++) {
                result = (result << 8) | (hash[i] & 0xff);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            return item.hashCode(); // Fallback (Önerilmez)
        }
    }

    public void add(String item) {
        long x = hash(item);
        int j = (int) (x >>> (64 - p)); // İlk p bit indeks
        long w = x << p;               // Geri kalan bitler

        // İlk '1'den önceki sıfır sayısı + 1
        byte rho = (byte) (Long.numberOfLeadingZeros(w | (1L << (p - 1))) + 1);
        if (rho > registers[j]) {
            registers[j] = rho;
        }
    }

    public double estimate() {
        double sum = 0;
        for (byte r : registers) {
            sum += Math.pow(2.0, -r);
        }

        double estimate = alpha * m * m * (1.0 / sum);

        // Küçük veri seti düzeltmesi (Linear Counting)
        if (estimate <= 2.5 * m) {
            int v = 0;
            for (byte r : registers) if (r == 0) v++;
            if (v != 0) {
                estimate = m * Math.log((double) m / v);
            }
        }
        return estimate;
    }

    public void merge(HyperLogLog other) {
        if (this.p != other.p) throw new IllegalArgumentException("p değerleri eşleşmiyor!");
        for (int i = 0; i < m; i++) {
            this.registers[i] = (byte) Math.max(this.registers[i], other.registers[i]);
        }
    }

    public static void main(String[] args) {
        int p = 12; // Hassasiyet (Hassasiyeti buradan değiştirebilirsin)
        int gercekAdet = 100000;
        HyperLogLog hll = new HyperLogLog(p);

        System.out.println("HLL Algoritması Test Ediliyor...");

        for (int i = 0; i < gercekAdet; i++) {
            hll.add("user_" + i); // Veriler ekleniyor
        }

        double tahmin = hll.estimate();
        double hataPayi = Math.abs(tahmin - gercekAdet) / (double) gercekAdet * 100;

        // Profesyonel çıktı paneli
        System.out.println("-------------------------------------------");
        System.out.println(">>> HLL CARDINALITY TAHMIN RAPORU <<<");
        System.out.println("-------------------------------------------");
        System.out.printf("Gerçek Öğe Sayısı     : %,d\n", gercekAdet);
        System.out.printf("HLL Tahmin Değeri     : %,d\n", (long) tahmin);
        System.out.printf("Hesaplanan Hata       : %% %.2f\n", hataPayi);
        System.out.printf("Kullanılan Kova (m)   : %d (p=%d)\n", (1 << p), p);
        System.out.println("-------------------------------------------");
        System.out.println("Durum: Analiz Başarıyla Tamamlandı.");
    }
}
