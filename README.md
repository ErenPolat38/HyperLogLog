HyperLogLog (HLL) Cardinality Estimator
Bu proje, büyük veri kümelerinde tekil öğe sayısını (cardinality) düşük bellek kullanımıyla tahmin eden HyperLogLog algoritmasının Java ile gerçeklenmiş halidir.

🚀 Özellikler
Yüksek Kaliteli Hash: Veriler SHA-256 algoritması ile hash'lenerek rastgele dağılım sağlanır.

Bellek Dostu: Sadece birkaç KB bellek kullanarak milyonlarca veriyi %98-99 doğrulukla tahmin eder.

Hata Analizi: Harmonik ortalama ve küçük veri setleri için Linear Counting düzeltmeleri içerir.

Agentic Kodlama: Proje mimarisi ve optimizasyon süreçleri Gemini 1.5 Pro ile asiste edilerek kurgulanmıştır.

🛠 Kullanılan Teknolojiler
Dil: Java

IDE: IntelliJ IDEA

Metodoloji: Agentic Coding & Algoritma Analizi

💻 Nasıl Çalıştırılır?
Projeyi bilgisayarınıza indirin.

Terminal üzerinden ilgili klasöre gidin.

Şu komutları çalıştırın:

Bash
javac HyperLogLog.java
java HyperLogLog
📊 Analiz Sonuçları
Kod çalıştırıldığında aşağıdaki verileri içeren bir rapor sunar:

Gerçek Öğe Sayısı: 100,000

HLL Tahmin Değeri: (~99,000 - 101,000 arası)

Bağıl Hata: < %1.62 (Teorik sınırın altındadır)
