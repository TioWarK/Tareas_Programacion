import numpy as np 
import matplotlib.pyplot as plt
from scipy.fft import fft, fftshift, fftfreq

#------------------------------------------------------------------------
# Parámetros de la señal
#------------------------------------------------------------------------

Fs = 1000  # Frecuencia de muestreo (Hz) mostrados en segundos
T = 1/Fs   # Periodo de muestreo (segundos)
L = 2000   # Longitud de la señal 2 egundos de duración de la señal (s) 
t = np.arange(-L//2, L//2) * T  # Vector de tiempo centrado en 0 -1 a 1 segundo

#------------------------------------------------------------------------
# Definición de la señal elemental en el tiempo
#------------------------------------------------------------------------

#Pulso rectangular
ancho_pulso = 0.2 
pulso_rectangular = np.where(np.abs(t) <= ancho_pulso/2, 1.0, 0.0)

#Función Senodial Pura 
f0 = 10 
senal_senoidal = np.sin(2 - np.pi *f0 * t)

#Función Escalón Unitario
escalon_unitario = np.where(t >= 0, 1.0, 0.0)

#------------------------------------------------------------------------
# Cálculo de la Transformada de Fourier
#------------------------------------------------------------------------
# Definimos una función para calcular la transformada de Fourier y su magnitud
def calcular_fft(senal, Fs, L):
    # Calculamos la FFT rápida 
    X_raw = fft(senal)

    # Centramos las frecuencias usando fftshift 
    X_shifted = fftshift(X_raw)

    # Obtenemos el vector de frecuencias correspondiente
    frecuencias = fftshift(fftfreq(L, 1/Fs))

    # Extraemos la magnitud de la transformada de Fourier y fase en radianes
    magnitud = np.abs(X_shifted)
    fase = np.angle(X_shifted)

    # Filtramos el ruido mumérico en la fase donde la magnitud es casi cero
    fase[magnitud < 1e-4] = 0.0

    return frecuencias, magnitud, fase

# Aplicamos la función a cada señal
f_pulso, mag_pulso, fase_pulso = calcular_fft(pulso_rectangular, Fs, L)
f_seno, mag_seno, fase_seno = calcular_fft(senal_senoidal, Fs, L)
f_esc, mag_esc, fase_esc = calcular_fft(escalon_unitario, Fs, L)

print("Transformada de Fourier calculada para las señales.")

# Visualización de los resultados
def graficar_analisis(t, senal, f, magnitud, fase, titulo_senal):
    # Creamos la figura con 3 sub-gráficas verticales
    fig, axs = plt.subplots(3, 1, figsize=(10, 8))
    fig.suptitle(f'Análisis de Fourier: {titulo_senal}', fontsize=14, fontweight='bold')
    
    # Grafica en el dominio del tiempo
    axs[0].plot(t, senal, color='tab:blue', linewidth=2)
    axs[0].set_title('Señal en el dominio del tiempo')
    axs[0].set_xlabel('Tiempo (segundos)')
    axs[0].set_ylabel('Amplitud')
    axs[0].grid(True)
    
    # Magnitud del Espectro
    axs[1].plot(f, magnitud, color='tab:red', linewidth=2)
    axs[1].set_title('Magnitud del Espectro de Frecuencia')
    axs[1].set_xlabel('Frecuencia (Hz)')
    axs[1].set_ylabel('Magnitud')
    axs[1].set_xlim([-50, 50])
    axs[1].grid(True)
    
    # Fase del Espectro
    axs[2].plot(f, fase, color='tab:green', linewidth=1.5, linestyle='--')
    axs[2].set_title('Fase del Espectro de Frecuencia')
    axs[2].set_xlabel('Frecuencia (Hz)')
    axs[2].set_ylabel('Fase (rad)')
    axs[2].set_xlim([-50, 50])
    axs[2].grid(True)
    
    plt.tight_layout()

# Generamos las gráficas para cada señal
graficar_analisis(t, pulso_rectangular, f_pulso, mag_pulso, fase_pulso, 'Pulso Rectangular')
graficar_analisis(t, senal_senoidal, f_seno, mag_seno, fase_seno, 'Señal Senoidal Pura')
graficar_analisis(t, escalon_unitario, f_esc, mag_esc, fase_esc, 'Señal Escalón Unitario')

# =============================================================================
# 5. DEMOSTRACIÓN PRÁCTICA: PROPIEDAD DE ESCALAMIENTO
# =============================================================================
ancho_angosto = 0.05
pulso_angosto = np.where(np.abs(t) <= ancho_angosto / 2, 1.0, 0.0)
f_angosto, mag_angosto, fase_angosto = calcular_fft(pulso_angosto, Fs, L)

fig, ax = plt.subplots(figsize=(10, 5))
ax.plot(f_pulso, mag_pulso, label='Pulso Original (Ancho = 0.2s)', color='tab:blue', linewidth=2)
ax.plot(f_angosto, mag_angosto, label='Pulso Comprimido (Ancho = 0.05s)', color='tab:red', linestyle='--', linewidth=2)
ax.set_title('Demostración de la Propiedad de Escalamiento', fontsize=12, fontweight='bold')
ax.set_xlabel('Frecuencia (Hz)')
ax.set_ylabel('Magnitud')
ax.set_xlim([-100, 100])
ax.grid(True)
ax.legend()

# Visualizamos las gráficas
plt.show()
