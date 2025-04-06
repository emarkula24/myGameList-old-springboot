
// import globals from 'globals'
// import reactHooks from 'eslint-plugin-react-hooks'
// import reactRefresh from 'eslint-plugin-react-refresh'
import tseslint from 'typescript-eslint'
import eslint from '@eslint/js'

export default tseslint.config(
  // { ignores: ['dist'] },
  // {
  //   extends: [eslint.configs.recommended, ...tseslint.configs.recommended],
  //   files: ['**/*.{ts,tsx}'],
  //   languageOptions: {
  //     ecmaVersion: 2020,
  //     globals: globals.browser,
  //   },

  //   plugins: {
  //     'react-hooks': reactHooks,
  //     'react-refresh': reactRefresh,
  //     '@typescript-esling': tseslint.plugin,
  //   },
  //   rules: {
  //     ...reactHooks.configs.recommended.rules,
  //     'react-refresh/only-export-components': [
  //       'warn',
  //       { allowConstantExport: true },
  //     ],
  //   },
  // },
  eslint.configs.recommended,
  tseslint.configs.recommended,
  
)
