import { defineConfig } from 'vite'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
    plugins: [
        tailwindcss()
    ],
    build: {
        outDir: '../resources/static/dist',
        emptyOutDir: true,
        manifest: true,
        assetsDir: '',
        rollupOptions: {
            input: {
                main: './index.js'
            }
        }
    }
})