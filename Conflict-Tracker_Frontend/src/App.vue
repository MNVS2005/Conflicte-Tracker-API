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
  max-width: 800px;
  margin: auto;
  font-family: Arial;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 20px;
}

input, select, button {
  padding: 6px;
}
</style>
