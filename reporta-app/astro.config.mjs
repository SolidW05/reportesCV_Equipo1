// @ts-check
import { defineConfig } from 'astro/config';

export default defineConfig({
  output: 'server',
  server: {
    port: 4321,
  },
  vite: {
    server: {
      proxy: {
        '/api': {
          target: 'http://localhost:8083',
          changeOrigin: true,
        },
      },
    },
  },
});
