package id.ac.unhas.infocovid19.model

data class DataKasus(
    val `data`: List<Data>
)

data class Data(
    val added_date: String? = "",
    val id_lab: Int? = 0,
    val id_pasien: Int? = 0,
    val id_status: Int? = 0,
    val jenis_kelamin: Int? = 0,
    val kode_pasien: Int? = 0,
    val provinsi: Int? = 0,
    val tampilkan: Int? = 0,
    val umur: Int? = 0,
    val wn: Int? = 0
)