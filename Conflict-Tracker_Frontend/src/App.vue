<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const conflicts = ref([])

const newConflict = ref({
  id: 0,
  name: '',
  startDate: '',
  status: 'ACTIVE',
  description: '',
  countryIds: []
})

const loadConflicts = async () => {
  try {
    const response = await axios.get('/api/admin/conflict')
    conflicts.value = response.data
  } catch (error) {
    console.error('Error cargando conflictos:', error)
  }
}

const createConflict = async () => {
  try {
    await axios.post('/api/admin/conflict', newConflict.value)
    
    newConflict.value = {
      id: 0,
      name: '',
      startDate: '',
      status: 'ACTIVE',
      description: '',
      countryIds: []
    }

    loadConflicts()
  } catch (error) {
    console.error('Error creando conflicto:', error)
  }
}

const deleteConflict = async (id) => {
  try {
    await axios.delete(`/api/admin/conflict/${id}`)
    loadConflicts()
  } catch (error) {
    console.error('Error eliminando conflicto:', error)
  }
}

onMounted(() => {
  loadConflicts()
})
</script>

<template>
  <div class="container">
    <h1>Gestión de Conflictos</h1>

    <div class="form">
      <input v-model="newConflict.name" placeholder="Nombre" />

      <input
        type="date"
        v-model="newConflict.startDate"
      />

      <select v-model="newConflict.status">
        <option value="ACTIVE">ACTIVE</option>
        <option value="INACTIVE">INACTIVE</option>
        <option value="FINISHED">FINISHED</option>
      </select>

      <input
        v-model="newConflict.description"
        placeholder="Descripción"
      />

      <input
        v-model.number="newConflict.countryIds[0]"
        type="number"
        placeholder="ID País"
      />

      <button @click="createConflict">
        Crear
      </button>
    </div>

    <hr />

    <ul>
      <li v-for="conflict in conflicts" :key="conflict.id">
        <strong>{{ conflict.name }}</strong> -
        {{ conflict.status }} -
        {{ conflict.startDate }}

        <button @click="deleteConflict(conflict.id)">
          Eliminar
        </button>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.container {
  max-width: 900px;
  margin: 40px auto;
  font-family: Inter, system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial;
  color: #0f172a;
  padding: 22px;
  background: linear-gradient(180deg, #ffffff 0%, #f8fafc 100%);
  border-radius: 12px;
  box-shadow: 0 8px 30px rgba(2,6,23,0.06);
}

h1 {
  font-size: 28px;
  margin: 0 0 16px 0;
  color: #0b1220;
}

.form {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-bottom: 20px;
  align-items: center;
}

.form input[type="date"],
.form input[type="number"],
.form input,
.form select {
  flex: 1 1 200px;
  min-width: 150px;
  padding: 10px 12px;
  border: 1px solid #e6edf3;
  border-radius: 10px;
  background: #ffffff;
  transition: border-color .14s ease, box-shadow .14s ease, transform .12s ease;
}

.form input:focus,
.form select:focus {
  outline: none;
  border-color: #7c3aed;
  box-shadow: 0 6px 20px rgba(124,58,237,0.10);
}

.form button {
  padding: 10px 14px;
  background: linear-gradient(90deg, #7c3aed, #4f46e5);
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 8px 24px rgba(79,70,229,0.14);
  transition: transform .12s ease, box-shadow .12s ease;
}

.form button:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 36px rgba(79,70,229,0.18);
}

hr {
  margin: 18px 0;
  border: none;
  height: 1px;
  background: linear-gradient(90deg, rgba(239,246,255,0), #eef2ff, rgba(239,246,255,0));
  border-radius: 1px;
}

ul {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
  gap: 12px;
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  background: #ffffff;
  padding: 14px;
  border-radius: 12px;
  border: 1px solid #eef4ff;
  box-shadow: 0 4px 18px rgba(2,6,23,0.04);
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 12px;
}

.info {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.info strong {
  font-size: 16px;
  color: #071029;
}

.meta {
  font-size: 13px;
  color: #475569;
}

li button {
  background: transparent;
  border: 1px solid #fca5a5;
  color: #b91c1c;
  padding: 8px 10px;
  border-radius: 8px;
  cursor: pointer;
  transition: background .12s ease, transform .12s ease;
}

li button:hover {
  background: #fff2f2;
  transform: translateY(-2px);
}

@media (max-width: 600px) {
  .form { flex-direction: column; }
  ul { grid-template-columns: 1fr; }
}
</style>
