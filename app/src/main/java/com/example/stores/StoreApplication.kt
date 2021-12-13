package com.example.stores

import android.app.Application
import androidx.room.Room

class StoreApplication : Application() {
    companion object{
        lateinit var database: StoreDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
            StoreDatabase::class.java,
            "StoreDatabase")
            .build()

        class StoreApplication : Application() {
            companion object{
                lateinit var database: StoreDatabase
            }

            override fun onCreate() {
                super.onCreate()

                val MIGRATION_1_2 = object : Migration(1, 2){
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE StoreEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT ''")
                    }
                }

                database = Room.databaseBuilder(this,
                    StoreDatabase::class.java,
                    "StoreDatabase")
                    .addMigrations(MIGRATION_1_2)
                    .build()

            }

        }

        override fun getItemCount(): Int = stores.size

        fun setStores(stores: MutableList<StoreEntity>) {
            this.stores = stores
            notifyDataSetChanged()
        }

        fun add(storeEntity: StoreEntity) {
            if (!stores.contains(storeEntity)) {
                stores.add(storeEntity)
                notifyItemInserted(stores.size-1)
            }
        }

        fun update(storeEntity: StoreEntity) {
            val index = stores.indexOf(storeEntity)
            if (index != -1){
                stores.set(index, storeEntity)
                notifyItemChanged(index)
            }
        }

        fun delete(storeEntity: StoreEntity) {
            val index = stores.indexOf(storeEntity)
            if (index != -1){
                stores.removeAt(index)
                notifyItemRemoved(index)
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val binding = ItemStoreBinding.bind(view)

            fun setListener(storeEntity: StoreEntity){
                with(binding.root) {
                    setOnClickListener { listener.onClick(storeEntity.id) }
                    setOnLongClickListener {
                        listener.onDeleteStore(storeEntity)
                        true
                    }
                }

                binding.cbFavorite.setOnClickListener{
                    listener.onFavoriteStore(storeEntity)
                }
            }

        }