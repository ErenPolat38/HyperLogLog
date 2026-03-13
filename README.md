# 📊 HyperLogLog (HLL) Cardinality Estimator

Bu proje, büyük veri kümelerinde tekil öğe sayısını (**cardinality**) düşük bellek kullanımıyla tahmin eden **HyperLogLog** algoritmasının Java ile gerçeklenmiş halidir.

---

## ✨ Özellikler
* **🚀 Yüksek Kaliteli Hash:** Veriler SHA-256 algoritması ile hash'lenerek rastgele dağılım sağlanır.
* **🧠 Bellek Dostu:** Sadece birkaç KB bellek kullanarak milyonlarca veriyi %98-99 doğrulukla tahmin eder.
* **📈 Hata Analizi:** Harmonik ortalama ve küçük veri setleri için *Linear Counting* düzeltmeleri içerir.
* **🤖 Agentic Kodlama:** Proje mimarisi ve optimizasyon süreçleri Gemini 1.5 Pro ile asiste edilerek kurgulanmıştır.

---

## 🛠 Kullanılan Teknolojiler
* **Dil:** Java
* **IDE:** IntelliJ IDEA
* **Metodoloji:** Agentic Coding & Algoritma Analizi

---

## 💻 Nasıl Çalıştırılır?
1. Projeyi bilgisayarınıza indirin.
2. Terminal üzerinden ilgili klasöre gidin.
3. Şu komutları çalıştırın:

```bash
javac HyperLogLog.java
java HyperLogLog
