import { defineConfig } from 'vite'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
    plugins: [
        tailwindcss()
    ],
    build: {
        outDir: '../resources/static',
        rollupOptions: {
            input: {
                main: './style.css'
            },
            output: {
                assetFileNames: 'main.css'
            }
        }
    }
})