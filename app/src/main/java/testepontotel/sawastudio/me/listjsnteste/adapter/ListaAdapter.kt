package testepontotel.sawastudio.me.listjsnteste.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import testepontotel.sawastudio.me.listjsnteste.R
import testepontotel.sawastudio.me.listjsnteste.utils.Pessoa

class ListaAdapter (private val mPessoas: List<Pessoa>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val psView = inflater.inflate(R.layout.row_list, parent, false)

        return PessoaHolder(psView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieHolder = holder as PessoaHolder
        val pessoa = this.mPessoas[position]

        movieHolder.nome.text = pessoa.name
        movieHolder.pwd.text = pessoa.pwd.toString()
        movieHolder.id.text = pessoa.id
    }


    override fun getItemCount(): Int {
        return this.mPessoas.size
    }


    internal inner class PessoaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nome: TextView
        var pwd: TextView
        var id: TextView

        init {
            nome = itemView.findViewById<View>(R.id.txt_nome) as TextView
            pwd = itemView.findViewById<View>(R.id.txt_pwd) as TextView
            id = itemView.findViewById<View>(R.id.txt_id) as TextView

        }

    }
}